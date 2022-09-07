package org.example.telegramBot.service.impl.financeService.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = Page.Builder.class)
public class Page<G> {
    private final List<G> content;
    @JsonProperty("total_elements")
    private final Integer totalElements;
    private final Integer size;
    @JsonProperty("total_pages")
    private final Integer totalPages;

    public Page(Builder<G> builder) {
        this.content = builder.content;
        this.totalElements = builder.totalElements;
        this.size = builder.size;
        this.totalPages = builder.totalPages;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder<G> {
        private List<G> content;
        @JsonProperty("total_elements")
        private Integer totalElements;
        private Integer size;
        @JsonProperty("total_pages")
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

    @Override
    public String toString() {
        return "Page{" +
                "content=" + content+
                ", totalElements=" + totalElements +
                ", size=" + size +
                ", totalPages=" + totalPages +
                '}';
    }
}