package com.hexq.qh.common.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 图像工具类
 * 对于CMYK模式的图像，由于JDK的Bug，目前还不能够处理,这些问题可以JAI.create()来代替ImageIO.read()解决。
 * 而高清图的内存溢出OOM问题只能使用ImageMagick转换
 * @author hexq
 * @date 2018/5/21 17:13
 */
public class ImageUtils {

    /**
     * 若图片横比200小，高比300小，不变
     * 若图片横比200小，高比300大，高缩小到300，图片比例不变
     * 若图片横比200大，高比300小，横缩小到200，图片比例不变
     * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
     * @param is 输入流
     * @param toFile 输出的文件路径
     * @param keepAspectRatio 是否按比例缩放，默认是按照比例缩放的
     * @param width 宽
     * @param height 高
     * @throws IOException IOException
     */
    public static void scalePixel(InputStream is, String toFile, boolean keepAspectRatio, int width, int height)
            throws IOException {
        Thumbnails.of(is)
                .keepAspectRatio(keepAspectRatio)
                .size(width, height)
                .toFile(toFile);
    }

    /**
     * 将图片按比例缩放
     * @param is 输入流
     * @param toFile 输出的文件路径
     * @param scale 比例
     * @throws IOException IOException
     */
    public static void scale(InputStream is, String toFile, float scale) throws IOException {
        Thumbnails.of(is)
                .scale(scale)
                .toFile(toFile);
    }

    /**
     * 将图片旋转
     * @param is 输入流
     * @param toFile 输出的文件路径
     * @param angle 角度,正数：顺时针 负数：逆时针
     * @param width 宽
     * @param height 高
     * @throws IOException IOException
     */
    public static void rotate(InputStream is, String toFile, int angle, int width, int height) throws IOException {
        Thumbnails.of(is)
                .size(width, height)
                .rotate(angle)
                .toFile(toFile);
    }

    /**
     * 图片加水印
     * @param is 输入流
     * @param toFile 输出的文件路径
     * @param watermarkPath 水印图片路径
     * @param quality 图像质量
     * @param opacity 水印透明度
     * @param width 宽
     * @param height 高
     * @throws IOException IOException
     */
    public static void watermark(InputStream is, String toFile, String watermarkPath, Positions positions, float quality,
                                 float opacity, int width, int height) throws IOException {
        Thumbnails.of(is)
                .size(width, height)
                .watermark(positions, ImageIO.read(new File(watermarkPath)), opacity)
                .outputQuality(quality)
                .toFile(toFile);
    }

    /**
     * 裁剪图片
     * @param is 输入流
     * @param toFile 输出文件路径
     * @param positions 相对位置
     * @param width 裁剪出的图片宽度
     * @param height 裁剪出的图片高度
     * @param keepAspectRatio 是否保持比例
     * @throws IOException IOException
     */
    public static void cut(InputStream is, String toFile, Positions positions, int width, int height,
                           boolean keepAspectRatio) throws IOException {
        Thumbnails.of(is)
                .sourceRegion(positions, width,height)
                .size(width, height)
                .keepAspectRatio(keepAspectRatio)
                .toFile(toFile);
    }

    /**
     * 指定坐标裁剪图片
     * @param is 输入流
     * @param toFile 输出文件路径
     * @param x 横坐标
     * @param y 纵坐标
     * @param width 裁剪出的图片宽度
     * @param height 裁剪出的图片高度
     * @param keepAspectRatio 是否保持比例
     * @throws IOException IOException
     */
    public static void cutByCoordinate(InputStream is, String toFile, int x, int y, int width, int height,
                                       boolean keepAspectRatio) throws IOException {
        Thumbnails.of(is)
                .sourceRegion(x, y, width, height)
                .keepAspectRatio(keepAspectRatio)
                .toFile(toFile);
    }

    /**
     * 改变图片格式
     * @param is 输入流
     * @param toFile 输出文件路径
     * @param format 图像格式
     * @throws IOException IOException
     */
    public static void format(InputStream is, String toFile, String format) throws IOException {
        Thumbnails.of(is)
                .outputFormat(format)
                .toFile(toFile);
    }


    public static void toOutputStream(File is) throws IOException {
        //toOutputStream(流对象)
        OutputStream os = new FileOutputStream(is);
        Thumbnails.of(is)
                .toOutputStream(os);
    }

    public static void asBufferedImage(File file, File thumb, String format) throws IOException {
        //asBufferedImage() 返回BufferedImage
        BufferedImage thumbnail = Thumbnails.of(thumb)
                .asBufferedImage();
        ImageIO.write(thumbnail, format, file);
    }

    public static String cover(String text, String bgImgPath, InputStream avatarFile, String toPath) throws IOException {
        toPath = toPath.endsWith("/") ? toPath : toPath + "/";
        // 生成的文件位置
        String filename = toPath + "tmp_" + UUIDUtil.getUUid() + ".jpg";
        Image avatar = ImageIO.read(avatarFile);
        Image bgImg = ImageIO.read(new File(bgImgPath));
        // 背景图的宽高
        int height = 1920;
        int width = 1080;
        // 构造一个类型为预定义图像类型之一的BufferedImage
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 绘制合成图像
        Graphics2D g = tag.createGraphics();
        g.drawImage(bgImg, 0, 0, width, height, null);
        g.drawImage(avatar, 634, 247, 357 , 357, null);
        // 设置字体
        g.setFont(new Font("宋体-简", Font.PLAIN, 64));
        // 抗锯齿平滑边缘
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 绘制文本
        text = text.length() > 8 ? text.substring(0, 8) : text;
        if (text.length() > 3) {
            g.drawString(text, 102, 610);
        } else {
            g.drawString(text, 441, 508);
        }
        // 生成绘制的图像
        ImageIO.write(tag, "jpg", new File(filename));
        // 释放此图形的上下文以及它使用的所有系统资源
        g.dispose();
        return filename;
    }

}
