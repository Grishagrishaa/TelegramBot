package org.example.telegramBot.service.impl.financeService.pagination;

public class PageRequest {
    private Integer page;
    private Integer size;

    public PageRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public static PageRequest of(Integer page, Integer size){
        return new PageRequest(page, size);
    }

    public static PageRequest defaults(){
        return new PageRequest(1, 5);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
