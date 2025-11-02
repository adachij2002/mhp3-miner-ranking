package mh.miner.service;

import javax.ws.rs.QueryParam;

public abstract class Pagination {
    @QueryParam("q_pageIndex")
    private int pageIndex;

    @QueryParam("q_pageSize")
    private int pageSize;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return pageIndex * pageSize;
    }
}
