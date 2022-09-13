package org.example.telegramBot.service.impl.financeService.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.example.telegramBot.service.impl.financeService.dto.api.ICurrencyDto;

@JsonDeserialize(builder = CurrencyMyfinNbrbDto.Builder.class)
public class CurrencyMyfinNbrbDto implements ICurrencyDto {
    @JsonProperty("currency_name")
    private final String currencyName;
    private final Float rate;
    private final String code;
    private final Integer scale;

    private CurrencyMyfinNbrbDto(Builder builder) {
        this.currencyName = builder.currencyName;
        this.rate = builder.rate;
        this.code = builder.code;
        this.scale = builder.scale;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public Float getRate() {
        return rate;
    }

    public String getCode() {
        return code;
    }

    public Integer getScale() {
        return scale;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder{
        @JsonProperty("currency_name")
        private String currencyName;
        private Float rate;
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
            return new CurrencyMyfinNbrbDto(this);
        }
    }

    @Override
    public String toString() {
        return "Currency=" + currencyName +
                " Rate=" + rate +
                " Code=" + code +
                " Scale=" + scale;
    }
}
