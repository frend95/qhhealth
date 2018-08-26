package com.hfkd.qhhealth.common.Model;

import java.io.Serializable;

/**
 * @author hexq
 * @date 2018/8/26 09:35
 */
public class PageVo implements Serializable{
    private Integer page;
    private Integer size = 10;

    public Integer getPage() {
        return (page - 1) * this.size;
    }

    public void setPage(Integer page) {
        this.page = page <= 0 ? 1 : page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public PageVo() {
    }

    public PageVo(Integer page) {
        this.page = page;
    }

    public PageVo(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageVo{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}
