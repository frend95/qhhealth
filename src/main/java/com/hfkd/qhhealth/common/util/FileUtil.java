package com.hfkd.qhhealth.common.util;

import com.hfkd.qhhealth.common.constant.ContentTypeEnum;
import com.hfkd.qhhealth.common.exception.MyException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.ResourceBundle;

/**
 * @author hexq
 * @date 2018/5/16 15:08
 */
public class FileUtil {

    private static String domain;
    static{
        ResourceBundle bundle= ResourceBundle.getBundle("config");
        domain=bundle.getString("domain.site");
    }

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
     * @param isUuidFileName 文件名是否重命名为uuid
     * @param isDatePath 是否按日期创建目录
     * @return 文件名
     */
    public static String upload(MultipartFile file, String modelFilePath, boolean isUuidFileName, boolean isDatePath)
            throws IOException {
        if (!modelFilePath.endsWith("/")) {
            modelFilePath = modelFilePath + "/";
        }
        String originalFilename = file.getOriginalFilename();
        if (isUuidFileName) {
            originalFilename = uuidFileName(originalFilename);
        }
        String datePath = "";
        if (isDatePath) {
            String[] date = DateUtil.yyyyMMddArr();
            datePath = date[0] + "/" + date[1] + "/" + date[2] + "/";
            //以年、月、日创建文件夹
            modelFilePath = modelFilePath + datePath;
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
        return datePath + originalFilename;
    }

    public static void write(String path, String name, byte[] bytes) throws IOException {
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        // 完整文件名
        String entireFileName = path + name;
        // 保存文件目标地址
        File fileEntF = new File(entireFileName);
        // 保存文件目标文件夹
        File fileP = new File(path);
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
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("文件上传失败");
        }
    }

    public static String writeHTML(String text, String name, String modelFilePath, boolean isDatePath)
            throws IOException {
        if (!modelFilePath.endsWith("/")) {
            modelFilePath = modelFilePath + "/";
        }
        if (StringUtils.isBlank(name)) {
            name = UUIDUtil.getUUid() + ".html";
        } else if (!name.endsWith(".html")) {
            name = name + ".html";
        }
        String datePath = "";
        if (isDatePath) {
            String[] date = DateUtil.yyyyMMddArr();
            datePath = date[0] + "/" + date[1] + "/" + date[2] + "/";
            //以年、月、日创建文件夹
            modelFilePath = modelFilePath + datePath;
        }
        // 完整文件名
        String entireFileName = modelFilePath + name;
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
            byte[] bytes = text.getBytes();
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("文本生成失败");
        }
        return datePath + name;
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

    public static String upload(List<MultipartFile> files, String modelFilePath, String domain, boolean uuidFileName,
                                boolean datePath) throws IOException {
        String fileUrls = null;
        if (files != null && files.size() != 0 && !files.get(0).isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (MultipartFile file : files) {
                String fileName = upload(file, modelFilePath, uuidFileName, datePath);
                sb.append(domain).append(fileName).append(",");
            }
            fileUrls = sb.substring(0, sb.length() - 1);
        }
        return fileUrls;
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

    public static String readTextFromUrl(String url, String path) throws IOException {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        if (!url.startsWith(domain)) {
            return null;
        }
        String htmlPath = path + FileUtil.getFileNameFromUrl(url);
        File file = new File(htmlPath);
        return FileUtils.readFileToString(file, "utf-8");
    }

    private static String getFileNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public static String getFilePathFromUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        return url.substring(url.lastIndexOf("/") - 10);
    }

    public static void delFile(String path) throws IOException {
        File file = new File(path);
        if (file.exists() && !file.delete()) {
            throw new IOException("File '" + file + "' cannot be delete");
        }
    }

    public static void delFileFromUrl(String url, String path) throws IOException {
        if (StringUtils.isBlank(url)) {
            return;
        }
        if (!url.startsWith(domain)) {
            return;
        }
        String fileName = path + getFileNameFromUrl(url);
        delFile(fileName);
    }
}
