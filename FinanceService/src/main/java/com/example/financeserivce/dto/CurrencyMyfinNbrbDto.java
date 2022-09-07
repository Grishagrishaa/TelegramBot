package com.example.financeserivce.dto;


import com.example.financeserivce.dto.api.ICurrencyDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CurrencyMyfinNbrbDto.Builder.class)
public class CurrencyMyfinNbrbDto implements ICurrencyDto {
    private final String currencyName;
    private final Float rate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Float tomorrowRate;
    private final String code;
    private final Integer scale;

    private CurrencyMyfinNbrbDto(Builder builder, Float tomorrowRate) {
        this.currencyName = builder.currencyName;
        this.rate = builder.rate;
        this.code = builder.code;
        this.scale = builder.scale;
        this.tomorrowRate = tomorrowRate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public Float getRate() {
        return rate;
    }

    public Float getTomorrowRate() {
        return tomorrowRate;
    }

    public String getCode() {
        return code;
    }

    public Integer getScale() {
        return scale;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder{
        private String currencyName;
        private Float rate;
        private  Float tomorrowRate;
        private String code;
        private Integer scale;

        public Builder setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
            return this;
        }

        public Builder setRate(Float rate) {
            this.rate = rate;
            return this;
        }

        public Builder setTomorrowRate(Float tomorrowRate) {
            this.tomorrowRate = tomorrowRate;
            return this;
        }

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setScale(Integer scale) {
            this.scale = scale;
            return this;
        }

        public static Builder create(){
            return new Builder();
        }

        public CurrencyMyfinNbrbDto build(){
            return new CurrencyMyfinNbrbDto(this, tomorrowRate);
        }
    }

    @Override
    public String toString() {
        return "CurrencyDto{" +
                "currencyName='" + currencyName + '\'' +
                ", rate=" + rate +
                ", code='" + code + '\'' +
                ", scale=" + scale +
                '}';
    }
}
