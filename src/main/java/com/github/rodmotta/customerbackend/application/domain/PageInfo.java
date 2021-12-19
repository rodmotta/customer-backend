package com.github.rodmotta.customerbackend.application.domain;

public class PageInfo {
    private int page;
    private int size;
    private String sort;
    private String direction;

    public PageInfo(int page, int size, String sort, String direction) {
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.direction = direction;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSort() {
        return sort;
    }

    public String getDirection() {
        return direction;
    }
}
