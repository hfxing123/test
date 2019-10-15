package com.example.common.util.base.model;

//import com.github.pagehelper.ISelect;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

//implements Closeable

public class Page<E>  {

    private static final long serialVersionUID = 1L;
    private int pageNum;
    private int pageSize;
    /*
    private int startRow;
    private int endRow;

     */

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }



    public int getPageNum() {
        return this.pageNum;
    }



    public int getPageSize() {
        return this.pageSize;
    }

    public Page<E> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /*
    public int getStartRow() {
        return this.startRow;
    }

    public Page<E> setStartRow(int startRow) {
        this.startRow = startRow;
        return this;
    }

    public int getEndRow() {
        return this.endRow;
    }

    public Page<E> setEndRow(int endRow) {
        this.endRow = endRow;
        return this;
    }
    */


    public String toString() {
//        return "Page{ pageNum=" + this.pageNum + ", pageSize=" + this.pageSize + ", startRow=" + this.startRow + ", endRow=" + this.endRow +  '}';
        return "Page{ pageNum=" + this.pageNum + ", pageSize=" + this.pageSize +  '}';
    }

}

