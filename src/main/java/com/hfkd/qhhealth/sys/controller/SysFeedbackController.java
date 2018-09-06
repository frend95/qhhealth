package com.hfkd.qhhealth.sys.controller;


import com.hfkd.qhhealth.common.annotation.LogOut;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.util.FileUtil;
import com.hfkd.qhhealth.common.util.RspEntity;
import com.hfkd.qhhealth.sys.mapper.SysFeedbackMapper;
import com.hfkd.qhhealth.sys.model.SysFeedback;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 反馈建议 Controller
 * @author hexq
 * @date 2018/7/5 10:12
 */
@RestController
@RequestMapping("/sys")
public class SysFeedbackController {

    @Autowired
    private SysFeedbackMapper feedbackMapper;
    @Value("${file.path.img}")
    private String imgPath;
    @Value("${domain.image}")
    private String imgDomain;

    @LogOut("用户反馈")
    @Verify
    @RequestMapping("/feedBack")
    public Map feedBack(String content, String mobile, List<MultipartFile> img, String type) throws IOException {
        if (StringUtils.isBlank(content) || StringUtils.isBlank(mobile) || StringUtils.isBlank(type)) {
            return RspEntity.error("信息不完整");
        }
        String fileName = null;
        if (img != null && img.size() != 0 && !img.get(0).isEmpty()) {
            List<String> fileNames = FileUtil.upload(img, imgPath, true, true);
            StringBuilder sb = new StringBuilder();
            for (String name : fileNames) {
                sb.append(name).append(",");
            }
            fileName = sb.substring(0, sb.length() - 1);
        }
        SysFeedback feedback = new SysFeedback(content, fileName, mobile, type);
        feedbackMapper.insert(feedback);
        return RspEntity.okMsg("感谢您的反馈！");
    }
}
