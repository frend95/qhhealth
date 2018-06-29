package com.hexq.qh.customer.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.hexq.qh.common.annotation.LogOut;
import com.hexq.qh.common.annotation.Verify;
import com.hexq.qh.common.constant.ConstVal;
import com.hexq.qh.common.util.DateUtil;
import com.hexq.qh.common.util.FileUploadUtil;
import com.hexq.qh.common.util.RspUtil;
import com.hexq.qh.common.util.SessionUtil;
import com.hexq.qh.customer.mapper.CustomerMapper;
import com.hexq.qh.customer.model.Customer;
import com.hexq.qh.customer.service.CustomerService;
import com.hexq.qh.healthlog.mapper.HealthLogMapper;
import com.hexq.qh.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 客户 Controller
 * @author hexq
 * @date 2018-06-06
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private HealthLogMapper healthLogMapper;
    @Autowired
    private UserMapper userMapper;
    @Value("${user.home}${file.path.img}")
//    @Value("${file.path.img}")
    private String imgPath;
    @Value("${user.home}${file.path.video}")
//    @Value("${file.path.video}")
    private String videoPath;

    @Verify
    @LogOut("查询客户列表")
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestBody Map<String, Object> params) throws Exception {
        Map<String, Object> resultMap = RspUtil.ok();
        String status = (String) params.get("status");
        if (!status.equals(ConstVal.CUSTOMER_BUY) && !status.equals(ConstVal.CUSTOMER_NOT_BUY)) {
            return RspUtil.error(resultMap, "客户状态错误");
        }
        String name = (String) params.get("name");
        Integer page = (Integer) params.get("page");
        Integer size = (Integer) params.get("size");
        Page<Customer> p = new Page<>(page, size);
        String userId = SessionUtil.getCurrId();
        List<Map<String, Object>> list = customerMapper.getConciseList(name, status, userId, p);
        resultMap.put("total", p.getTotal());
        resultMap.put("result", list);
        return resultMap;
    }

    @Verify
    @LogOut("添加客户")
    @RequestMapping("/add")
    public Map<String, Object> add(@RequestBody Customer customer) throws Exception {
        Map<String, Object> resultMap = RspUtil.ok();
        Customer customerDb = customerService.selectById(customer.getId());
        if (customerDb != null) {
            return RspUtil.error(resultMap, "已存在此客户");
        }
        String userId = SessionUtil.getCurrId();
        customer.setUserId(userId);
        customerService.insert(customer);
        // 营养师客户加一
        userMapper.customerAddOne(userId);
        return resultMap;
    }

    @LogOut("客户详情")
    @RequestMapping("/detail")
    public Map<String, Object> detail(@RequestBody Map<String, Object> params) {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = (String) params.get("id");
        Customer customer = customerService.selectById(id);
        resultMap.put("info", customer);
        // 只取最近两张照片和视频
        List<Map<String, Object>> images = customerMapper.getMedia(id, ConstVal.MEDIA_TYPE_IMAGE, 0, 2);
        List<Map<String, Object>> videos = customerMapper.getMedia(id, ConstVal.MEDIA_TYPE_VIDEO, 0, 2);
        String endTime = DateUtil.tomorrow();
        String startTime = DateUtil.pastDate(60);
        List<Map<String, Object>> weightLog = healthLogMapper.getWeightLog(id, startTime, endTime);
        resultMap.put("images", images);
        resultMap.put("videos", videos);
        resultMap.put("weightLog", weightLog);
        return resultMap;
    }

    @LogOut("查询客户上传媒体")
    @RequestMapping("/mediaList")
    public Map<String, Object> mediaList(@RequestBody Map<String, Object> params) {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = (String) params.get("id");
        String type = (String) params.get("type");
        Integer page = (Integer) params.get("page");
        Integer size = (Integer) params.get("size");
        page = page <= 0 ? 0 : (page - 1) * size;
        List<Map<String, Object>> media = customerMapper.getMedia(id, type, page, size);
        resultMap.put("result", media);
        return resultMap;
    }

    @LogOut("用户媒体观看次数加一")
    @RequestMapping("/watched")
    public Map<String, Object> watched(@RequestBody Map<String, Object> params) {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = (String) params.get("id");
        customerMapper.watchAddOne(id);
        return resultMap;
    }

    @Verify
    @LogOut("删除客户")
    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestBody Map<String, Object> params) throws Exception {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = (String) params.get("id");
        String userId = SessionUtil.getCurrId();
        Customer customer = new Customer();
        customer.setId(id);
        customer.setStatus(ConstVal.CUSTOMER_INVALID);
        boolean isSuccess = customerService.updateById(customer);
        // 营养师客户数减一
        if (isSuccess) {
            userMapper.customerSubOne(userId);
        }
        resultMap = isSuccess ? resultMap : RspUtil.error(resultMap, "此用户不存在");
        return resultMap;
    }

    @Verify
    @LogOut("客户评分")
    @RequestMapping("/star")
    public Map<String, Object> star(@RequestBody Map<String, Object> params) {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = (String) params.get("id");
        Integer star = (Integer) params.get("star");
        if (star > 5 || star < 0) {
            return RspUtil.error(resultMap, "超出评分范围");
        }

        Customer customer = new Customer();
        customer.setId(id);
        customer.setStar(star);
        boolean isSuccess = customerService.updateById(customer);
        resultMap = isSuccess ? resultMap : RspUtil.error(resultMap, "此用户不存在");
        return resultMap;
    }

    @Verify
    @LogOut("设置客户购买状态")
    @RequestMapping("/status")
    public Map<String, Object> status(@RequestBody Map<String, Object> params) {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = (String) params.get("id");
        Customer customer = customerService.selectById(id);
        if (customer == null) {
            return RspUtil.error(resultMap, "此用户不存在");
        }
        String status = customer.getStatus();
        status = ConstVal.CUSTOMER_NOT_BUY.equals(status) ? ConstVal.CUSTOMER_BUY : ConstVal.CUSTOMER_NOT_BUY;
        customer.setStatus(status);
        customerService.updateById(customer);
        resultMap.put("status", status);
        return resultMap;
    }

    @Verify
    @LogOut("设置客户VIP")
    @RequestMapping("/vip")
    public Map<String, Object> vip(@RequestBody Map<String, Object> params) throws Exception {
        Map<String, Object> resultMap = RspUtil.ok();
        String id = (String) params.get("id");
        String userId = SessionUtil.getCurrId();
        Customer customer = customerService.selectById(id);
        if (customer == null) {
            return RspUtil.error(resultMap, "此用户不存在");
        }
        String isVip = customer.getIsVip();
        boolean vip = ConstVal.VIP_YES.equals(isVip);
        isVip = vip ? ConstVal.VIP_NO : ConstVal.VIP_YES;
        customer.setIsVip(isVip);
        customerService.updateById(customer);
        // 营养师VIP客户数量加一或减一
        if (vip) {
            userMapper.vipAddOne(userId);
        } else {
            userMapper.vipSubOne(userId);
        }
        resultMap.put("isVip", isVip);
        return resultMap;
    }


    @LogOut("上传客户媒体文件")
    @RequestMapping(value = "/upload")
    public Map<String,Object> upload(@RequestParam("file")MultipartFile file,
                                     @RequestParam("id")String id,
                                     @RequestParam("type")String type,
                                     @RequestParam("title")String title,
                                     @RequestParam("desc")String desc) throws IOException {
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
        String filename = FileUploadUtil.upload(file, path, true);
        // todo 生成略缩图
        customerMapper.addMedia(id, filename, type, title, desc, null);
        resultMap.put("filename", filename);
        return resultMap;
    }
}
