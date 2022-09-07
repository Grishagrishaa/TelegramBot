package org.example.telegramBot.service.impl.financeService;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.telegramBot.service.impl.financeService.api.ICurrencyDto;

public class CurrencyMyfinDto implements ICurrencyDto {
    @JsonProperty("bank_name")
    private String bankName;
    @JsonProperty("currency_name")
    private String currencyName;
    @JsonProperty("bank_buy")
    private Float bankBuy;
    @JsonProperty("bank_sell")
    private Float bankSell;

    public CurrencyMyfinDto() {
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Float getBankBuy() {
        return bankBuy;
    }

    public void setBankBuy(Float bankBuy) {
        this.bankBuy = bankBuy;
    }

    public Float getBankSell() {
        return bankSell;
    }

    public void setBankSell(Float bankSell) {
        this.bankSell = bankSell;
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
