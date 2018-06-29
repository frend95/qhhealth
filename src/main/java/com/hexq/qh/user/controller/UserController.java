package com.hexq.qh.user.controller;


import com.hexq.qh.common.annotation.LogOut;
import com.hexq.qh.common.annotation.Verify;
import com.hexq.qh.common.constant.ConstVal;
import com.hexq.qh.common.util.*;
import com.hexq.qh.user.mapper.UserMapper;
import com.hexq.qh.user.model.User;
import com.hexq.qh.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 用户 Controller
 * @author hexq
 * @date 2018-06-05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Value("${user.home}${file.path.img}")
//    @Value("${file.path.img}")
    private String imgPath;
    @Value("${user.home}${file.path.video}")
//    @Value("${file.path.video}")
    private String videoPath;
    @Value("${user.default-pwd}")
    private String defaultPwdMd5;

    @Verify(adminOnly = true)
    @LogOut("添加营养师")
    @RequestMapping("/add")
    public Map<String, Object> add(@RequestBody User user) {
        Map<String, Object> resultMap = RspUtil.ok();
        // todo 验证参数
        String account = user.getAccount();
        User userDb = userService.getByAccount(account);
        if (userDb != null) {
            return RspUtil.error(resultMap, "账号已存在");
        }
        user.setId(UUIDUtil.getUUid());
        //把生成的uuid作为盐
        String salt = UUIDUtil.getUUid();
        //使用自定算法加盐进行摘要
        String pwdSalt = DigestUtil.pwdSalt(defaultPwdMd5, salt);
        user.setPassword(pwdSalt);
        user.setSalt(salt);
        userService.insert(user);
        return resultMap;
    }

    @Verify
    @LogOut("更新密码")
    @RequestMapping("/updatePwd")
    public Map<String, Object> updatePwd(@RequestBody Map<String, Object> params) throws Exception {
        Map<String, Object> resultMap = RspUtil.ok();
        String oldPwd = (String) params.get("oldPassword");
        String newPwd = (String) params.get("newPassword");
        if (StringUtils.isBlank(newPwd) || StringUtils.isBlank(oldPwd)) {
            return RspUtil.error(resultMap, "信息不完整");
        }
        if (newPwd.length() < 6) {
            return RspUtil.error(resultMap, "密码不能小于6位");
        }
        if (oldPwd.equals(newPwd)) {
            return RspUtil.error(resultMap, "新旧密码不能相同");
        }
        String userId = SessionUtil.getCurrId();
        User user = userService.selectById(userId);
        String oldPwdSaltDb = user.getPassword();
        //旧密码md5
        String oldPwdMd5 = DigestUtil.md5(oldPwd);
        //盐为添加用户时随机生成的uuid
        String salt = user.getSalt();
        //使用自定算法计算加盐后的的旧密码
        String oldPwdSalt = DigestUtil.pwdSalt(oldPwdMd5, salt);
        //校验旧密码是否正确
        if (!oldPwdSaltDb.equals(oldPwdSalt)) {
            return RspUtil.error(resultMap, "旧密码不正确");
        }
        //计算新密码md5
        String newPwdMd5 = DigestUtil.md5(newPwd);
        String newPwdSalt = DigestUtil.pwdSalt(newPwdMd5, salt);
        user.setPassword(newPwdSalt);
        userService.updateById(user);
        return resultMap;
    }


    @Verify
    @LogOut("更新营养师")
    @RequestMapping("/update")
    public Map<String, Object> update(@RequestBody User user) {
        Map<String, Object> resultMap = RspUtil.ok();
        // todo 验证参数
        user.setAccount(null);
        user.setPassword(null);
        user.setStatus(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        userService.updateById(user);
        return resultMap;
    }

    @Verify
    @LogOut("营养师详情")
    @RequestMapping("/detail")
    public Map<String, Object> detail() throws Exception {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = SessionUtil.getCurrId();
        User user = userService.selectById(id);
        resultMap.put("info", user);
        // 只取最近两张照片和视频
        List<Map<String, Object>> images = userMapper.getMedia(id, ConstVal.MEDIA_TYPE_IMAGE, 0, 2);
        List<Map<String, Object>> videos = userMapper.getMedia(id, ConstVal.MEDIA_TYPE_VIDEO, 0, 2);
        Integer totalWeight = userMapper.getTotalWeightLoss(id);
        resultMap.put("images", images);
        resultMap.put("videos", videos);
        resultMap.put("totalWeight", totalWeight);
        return resultMap;
    }

    @LogOut("查询营养师上传媒体")
    @RequestMapping("/mediaList")
    public Map<String, Object> mediaList(@RequestBody Map<String, Object> params) {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = (String) params.get("id");
        String type = (String) params.get("type");
        Integer size = (Integer) params.get("size");
        Integer page = (Integer) params.get("page");
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> media = userMapper.getMedia(id, type, page, size);
        resultMap.put("result", media);
        return resultMap;
    }

    @LogOut("营养师媒体观看次数加一")
    @RequestMapping("/watched")
    public Map<String, Object> watched(@RequestBody Map<String, Object> params) {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = (String) params.get("id");
        userMapper.watchAddOne(id);
        return resultMap;
    }

    @LogOut("查询营养师列表")
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestBody Map<String, Object> params) {
        Map<String, Object> resultMap = RspUtil.ok();
        Integer page = (Integer) params.get("page");
        Integer size = (Integer) params.get("size");
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> userList = userMapper.getUserList(page, size);
        resultMap.put("result", userList);
        return resultMap;
    }

    @LogOut("查询营养师预览")
    @RequestMapping("/preview")
    public Map<String, Object> preview(@RequestBody Map<String, Object> params) {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = (String) params.get("id");
        Map<String, Object> userPreview = userMapper.getUserPreview(id);
        resultMap.put("result", userPreview);
        return resultMap;
    }

    @LogOut("上传营养师媒体文件")
    @RequestMapping(value = "/upload")
    public Map<String,Object> upload(@RequestParam("file")MultipartFile file,
                                     @RequestParam("type")String type,
                                     @RequestParam("title")String title,
                                     @RequestParam("desc")String desc) throws Exception {
        Map<String,Object> resultMap = RspUtil.ok();
        String path;
        switch (type) {
            case ConstVal.MEDIA_TYPE_IMAGE:
                path = imgPath;
                break;
            case ConstVal.MEDIA_TYPE_VIDEO:
                path = videoPath;
                break;
            default:
                return RspUtil.error(resultMap, "媒体类型错误");
        }
        String userId = SessionUtil.getCurrId();
        String filename = FileUploadUtil.upload(file, path, true);
        // todo 生成略缩图
        userMapper.addMedia(userId, filename, type, title, desc, null);
        resultMap.put("filename", filename);
        return resultMap;
    }
}
