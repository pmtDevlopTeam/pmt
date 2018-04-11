package com.camelot.pmt.platform.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Title: DataGrid.java
 * @Description: TODO(分页工具类(配合Pager使用))
 * @author: jh
 */
public class DataGrid<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 总条数
     */
    private Long total = 0L;

    /**
     * 查询数据集合
     */
    private List<T> rows = new ArrayList<T>();

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
