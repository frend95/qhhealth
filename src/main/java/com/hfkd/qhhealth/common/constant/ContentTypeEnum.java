package com.hfkd.qhhealth.common.constant;

import java.util.LinkedList;
import java.util.List;

/**
 * content-type
 * @author hexq
 * @date 2018/5/30 15:04
 */
public enum ContentTypeEnum {
    TEXT("text/xml"), GIF("image/gif"), ICON("image/x-icon"),
    JPG("image/jpeg"), MP4("video/mpeg4"), PNG("image/png"),
    FLASH("application/x-shockwave-flash"), APK("application/vnd.android.package-archive"), XLS("application/excel"),
    TXT("text/plain"), TORRENT("application/x-bittorrent"), MP3("audio/mp3"),
    EXE("application/exe"), AVI("video/avi"), BMP("image/bmp"),
    DOC("application/doc"), PPT("application/ppt"), STREAM("application/octet-stream"),
    GZ("application/gzip"), ZIP("application/zip"), RAR("application/rar"),
    JSON("application/json"), XML("application/xml");

    private String type;

    ContentTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static List<ContentTypeEnum> getImage() {
        LinkedList<ContentTypeEnum> list = new LinkedList<>();
        list.add(JPG);
        list.add(PNG);
        list.add(ICON);
        list.add(GIF);
        list.add(BMP);
        return list;
    }

    public static List<ContentTypeEnum> getVideo() {
        LinkedList<ContentTypeEnum> list = new LinkedList<>();
        list.add(MP4);
        list.add(AVI);
        list.add(FLASH);
        return list;
    }
}

