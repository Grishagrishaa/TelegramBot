package org.example.telegramBot.service.impl.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.example.telegramBot.service.impl.currency.api.ICurrencyDto;

@JsonDeserialize(builder = CurrencyMyfinDto.Builder.class)
public class CurrencyMyfinDto implements ICurrencyDto {
    @JsonProperty
    private final String bankName;
    @JsonProperty
    private final String currencyName;
    @JsonProperty
    private final Float bankBuy;
    @JsonProperty
    private final Float bankSell;

    private CurrencyMyfinDto(Builder builder) {
        this.bankName = builder.bankName;
        this.currencyName = builder.currencyName;
        this.bankBuy = builder.bankBuy;
        this.bankSell = builder.bankSell;
    }

    public String getBankName() {
        return bankName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public Float getBankBuy() {
        return bankBuy;
    }

    public Float getBankSell() {
        return bankSell;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder{
        private String bankName;
        private String currencyName;
        private Float bankBuy;
        private Float bankSell;

        public Builder setBankName(String bankName) {
            this.bankName = bankName;
            return this;
        }

        public Builder setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
            return this;
        }

        public Builder setBankBuy(Float bankBuy) {
            this.bankBuy = bankBuy;
            return this;
        }

        public Builder setBankSell(Float bankSell) {
            this.bankSell = bankSell;
            return this;
        }

        public static Builder create(){
            return new Builder();
        }

        public CurrencyMyfinDto build(){
            return new CurrencyMyfinDto(this);
        }
    }

    @Override
    public String toString() {
        return
                "Bank=" + bankName +
                " currency=" + currencyName +
                " Buy=" + bankBuy +
                " Sell=" + bankSell;
    }
}
