package com.camelot.pmt.common;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Title: Pager.java
 * @Description: TODO(分页工具类 一般配合DattaGrid工具类使用)
 * @author: jh
 */
public class Pager<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_PAGESIZE = 10;

    private List<T> records;
    /**
     * 接收到的页码
     */
    private int page = 1;
    /**
     * 接收到的页数
     */
    private int rows = 10;

    /**
     * 计算后的页码
     */
    private int pageOffset;

    /**
     * 以下暂不使用
     */
    private String sort;
    private String order;
    private int totalPage;
    private long totalCount;
    private int startPageIndex;
    private int endPageIndex;
    private int pageCode = 10;
    private int previewPage = 1;
    private int nextPage = 1;

    public Pager() {
    }

    public Pager(int page, int rows) {
        this.page = page;
        this.rows = rows;
    }

    public Pager(int page) {
        this.setPage(page);
        this.rows = DEFAULT_PAGESIZE;
    }

    public int getPageOffset() {
        return (page - 1) * rows;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page > 0) {
            this.page = page;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows > 0) {
            this.rows = rows;
        }
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;

        this.startPageIndex = this.page - (pageCode % 2 == 0 ? pageCode / 2 - 1 : pageCode / 2);
        this.endPageIndex = this.page + pageCode / 2;
        if (this.startPageIndex < 1) {
            this.startPageIndex = 1;
            if (totalPage >= pageCode) {
                this.endPageIndex = pageCode;
            } else {
                this.endPageIndex = totalPage;
            }
        }
        if (this.endPageIndex > totalPage) {
            this.endPageIndex = totalPage;
            if ((this.endPageIndex - pageCode) > 0) {
                this.startPageIndex = this.endPageIndex - pageCode + 1;
            } else {
                this.startPageIndex = 1;
            }
        }
        if (this.endPageIndex <= 1) {
            this.endPageIndex = 1;
        }
        this.previewPage = this.page - 1;
        this.nextPage = this.page + 1;
        if (this.page <= 1) {
            this.previewPage = 1;
        }
        if (this.page >= this.totalPage) {
            this.nextPage = this.totalPage;
        }
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        setTotalPage((int) (totalCount % rows == 0 ? totalCount / rows : (totalCount / rows + 1)));
    }

    public int getStartPageIndex() {
        return startPageIndex;
    }

    public void setStartPageIndex(int startPageIndex) {
        this.startPageIndex = startPageIndex;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    public int getPageCode() {
        return pageCode;
    }

    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
    }

    public int getPreviewPage() {
        return previewPage;
    }

    public void setPreviewPage(int previewPage) {
        this.previewPage = previewPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

}
