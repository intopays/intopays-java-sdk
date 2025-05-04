package com.intopays.sdk.infra.http.response;

import java.util.List;

public class PageResponse<T> {
    private int pageNumber;
    private int pageSize;
    private long total;
    private List<T> body;

    public PageResponse() {
    }

    public PageResponse(int pageNumber, int pageSize, long total, List<T> body) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.total = total;
        this.body = body;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getBody() {
        return body;
    }

    public void setBody(List<T> body) {
        this.body = body;
    }
}

