package com.yifeistudio.space.unit.model;

/**
 * 分页请求
 *
 */
public class PageReq {

    private int index = 1;

    private int size = 10;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
