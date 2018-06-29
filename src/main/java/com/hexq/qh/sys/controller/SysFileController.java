package com.hexq.qh.sys.controller;


import com.hexq.qh.common.annotation.LogOut;
import com.hexq.qh.common.constant.ContentTypeEnum;
import com.hexq.qh.common.util.FileUploadUtil;
import com.hexq.qh.common.util.ImageUtils;
import com.hexq.qh.common.util.RspUtil;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author hexq
 * @date 2018/5/18 14:17
 */
@RestController
@RequestMapping("/sys")
public class SysFileController {
    private final Log log = LogFactory.getLog(getClass());

//    @Value("${user.home}${file.path.img}")
    @Value("${file.path.img}")
    private String filePath;
    @Value("${file.suffix}")
    private String suffix;
    @Value("${file.maxNumber}")
    private Integer maxNumber;
//    @Value("${user.home}${file.bgImgPath}")
    @Value("${file.bgImgPath}")
    private String bgImgPath;

    @LogOut("上传图片")
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public Map<String,Object> upload(@RequestParam("img")List<MultipartFile> files) throws IOException {
        Map<String,Object> resultMap = RspUtil.ok();
        List<String> nameLs = FileUploadUtil.upload(files, filePath, true);
        resultMap.put("nameLs", nameLs);
        return resultMap;
    }

    @LogOut("图片处理")
    @RequestMapping("/imgProcess")
    public ResponseEntity imgProcess(@RequestParam("img")MultipartFile file) throws IOException {
        if (!filePath.endsWith("/")) {
            filePath = filePath + "/";
        }
        String fileName = FileUploadUtil.uuidFileName(file.getOriginalFilename());
        String path = filePath + fileName;
        File fileP = new File(filePath);
        File newFile = new File(path);
        // 判断路径是否存在
        if (!fileP.exists() && !fileP.mkdirs()) {
            throw new IOException("Directory '" + fileP + "' could not be created");
        }
        try (InputStream is = file.getInputStream()) {
            ImageUtils.cut(is, path, Positions.BOTTOM_CENTER, 400, 400, false);
            return FileUploadUtil.buildRsp(newFile, ContentTypeEnum.JPG);
        } finally {
            if (newFile.exists() && !newFile.delete()) {
                log.error("File '" + newFile + "' cannot be delete");
            } else {
                log.info("File '" + newFile + "' has been deleted");
            }
        }
    }

    @LogOut("图片合成")
    @RequestMapping("/imgCover")
    public Map<String,Object> imgCover(@RequestParam("img")String avatarUrl,
                                   @RequestParam("num")String num,
                                   @RequestParam("name")String name) throws Exception {
        Map<String,Object> resultMap = RspUtil.ok();
        File cover = null;
        InputStream ais = FileUploadUtil.getFileByUrl(avatarUrl);
        try {
            String coverPath = ImageUtils.cover(name, bgImgPath + num + ".jpg", ais, bgImgPath);
            cover = new File(coverPath);
            byte[] bytes = FileUtils.readFileToByteArray(cover);
            String base64Str = Base64Utils.encodeToString(bytes);
            resultMap.put("img", base64Str);
            return resultMap;
        } finally {
            if (cover != null && cover.exists() && !cover.delete()) {
                log.error("File '" + cover + "' cannot be delete");
            } else {
                log.info("File '" + cover + "' has been deleted");
            }
        }
    }

    @LogOut("图片合成")
    @RequestMapping("/imgCover1")
    public Map<String,Object> imgCover1(@RequestParam("img")String avatarUrl,
                                       @RequestParam("num")String num,
                                       @RequestParam("name")String name) throws Exception {
        Map<String,Object> resultMap = RspUtil.ok();
        try (InputStream ais = FileUploadUtil.getFileByUrl(avatarUrl)) {
            String coverPath = ImageUtils.cover(name, bgImgPath + num + ".jpg", ais, bgImgPath);
            int i = coverPath.lastIndexOf("/");
            coverPath = coverPath.substring(i + 1);
            resultMap.put("img", coverPath);
            return resultMap;
        }
    }

    @LogOut("图片合成")
    @RequestMapping("/imgCover2")
    public Map<String,Object> imgCover2(@RequestBody Map<String,String> requestMap) throws Exception {
        String avatarUrl =  requestMap.get("img");
        String num = requestMap.get("num");
        String name = requestMap.get("name");
        Map<String,Object> resultMap = RspUtil.ok();
        try (InputStream ais = FileUploadUtil.getFileByUrl(avatarUrl)) {
            String coverPath = ImageUtils.cover(name, bgImgPath + num + ".jpg", ais, bgImgPath);
            int i = coverPath.lastIndexOf("/");
            coverPath = coverPath.substring(i + 1);
            resultMap.put("img", coverPath);
            return resultMap;
        }
    }

}
