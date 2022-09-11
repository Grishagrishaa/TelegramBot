package com.example.financeserivce.service.pagination;

import java.util.List;

public class Page<G> {
    private final List<G> content;
    private final Integer totalElements;
    private final Integer size;
    private final Integer totalPages;

    public Page(Builder<G> builder) {
        this.content = builder.content;
        this.totalElements = builder.totalElements;
        this.size = builder.size;
        this.totalPages = builder.totalPages;
    }

    public static class Builder<G> {
        private List<G> content;
        private Integer totalElements;
        private Integer size;
        private Integer totalPages;

        private Builder(){
        }

        public Builder<G> setContent(List<G> content) {
            this.content = content;
            return this;
        }

        public Builder<G> setTotalElements(Integer totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder<G> setSize(Integer size) {
            this.size = size;
            return this;
        }

        public Builder<G> setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public static<G> Builder<G> create(){
            return new Builder<>();
        }

        public Page<G> build(){
            return new Page<>(this);
        }
    }

    public List<G> getContent() {
        return content;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}