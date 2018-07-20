package com.hfkd.qhhealth.common.util;

import com.hfkd.qhhealth.common.constant.ContentTypeEnum;
import com.hfkd.qhhealth.common.exception.MyException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hexq
 * @date 2018/5/16 15:08
 */
public class FileUtil {

    /**
     * 将文件名改为uuid
     * @param fileName 原始文件名
     * @return 扩展名不变文件名为uuid
     */
    public static String uuidFileName(String fileName) {
        String extensionName = fileName.substring(fileName.lastIndexOf("."));
        return UUIDUtil.getUUid() + extensionName;
    }

    /**
     * 上传文件
     * @param file MultipartFile
     * @param modelFilePath 文件路径
     * @param uuidFileName 文件名是否重命名为uuid
     * @return 文件名
     */
    public static String upload(MultipartFile file, String modelFilePath, boolean uuidFileName, boolean datePath)
            throws IOException {
        if (!modelFilePath.endsWith("/")) {
            modelFilePath = modelFilePath + "/";
        }
        String originalFilename = file.getOriginalFilename();
        if (uuidFileName) {
            originalFilename = uuidFileName(originalFilename);
        }
        if (datePath) {
            String[] date = DateUtil.yyyyMMddArr();
            //以年、月、日创建文件夹
            modelFilePath = modelFilePath + date[0] + "/" + date[1] + "/" + date[2] + "/";
        }
        // 完整文件名
        String entireFileName = modelFilePath + originalFilename;
        // 保存文件目标地址
        File fileEntF = new File(entireFileName);
        // 保存文件目标文件夹
        File fileP = new File(modelFilePath);
        // 判断路径是否存在
        if (!fileP.exists() && !fileP.mkdirs()) {
            throw new IOException("Directory '" + fileP + "' could not be created");
        }
        // 判断文件是否存在,文件上传失败，可再次上传，因此先删除原来失败的文件
        if (fileEntF.exists() && !fileEntF.delete()) {
            throw new IOException("File '" + fileEntF + "' cannot be delete");
        }
        try (FileOutputStream fos = new FileOutputStream(fileEntF);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            //写入文件
            byte[] bytes = file.getBytes();
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("文件上传失败");
        }
        return originalFilename;
    }

    /**
     * 检查文件扩展名
     * @param suffixLs 扩展名list
     * @param file MultipartFile
     * @return 扩展名是否符合要求
     */
    public static boolean checkFileExtension(List<String> suffixLs, MultipartFile file) {
        String filename = file.getOriginalFilename();
        String thumbSubstring = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        return suffixLs.contains(thumbSubstring);
    }

    public static List<String> upload(List<MultipartFile> file, String modelFilePath, boolean uuidFileName,
                                      boolean datePath) throws IOException {
        List<String> nameLs = new LinkedList<>();
        for (MultipartFile multipartFile : file) {
            String fileName = upload(multipartFile, modelFilePath, uuidFileName, datePath);
            nameLs.add(fileName);
        }
        return nameLs;
    }

    public static void checkFileExtension(List<String> suffixLs, List<MultipartFile> file) {
        for (MultipartFile multipartFile : file) {
            boolean b = checkFileExtension(suffixLs, multipartFile);
            if (!b) {
                throw new MyException("扩展名不正确");
            }
        }
    }

    public static ResponseEntity buildRsp(File file, ContentTypeEnum type) throws IOException {
        FileSystemResource fsr = new FileSystemResource(file);
        HttpHeaders headers = new HttpHeaders();
        //当Content-Disposition=attachment时，下载图片
        //当Content-Disposition=inline时，直接在浏览器内打开该图片
        // todo 下载或展示逻辑判断
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(type.getType()))
                .body(new InputStreamResource(fsr.getInputStream()));
    }

    public static InputStream getFileByUrl(String url) throws Exception {
        URL url1 = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        return conn.getInputStream();
    }
}
