package com.base.springboot.common.macb.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表视图类
 * @param <T>
 */
public class ListVo<T> {
    //数据量
    private int totalSize = 0;
    //数据列表
    private List<T> list = new ArrayList();

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
