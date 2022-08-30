package org.example.telegramBot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class CurrencyDto {//TODO ADD SPRING VALIDATION
    @JsonProperty("Cur_ID")
    private Integer CurrencyId;
    @JsonProperty("Date")
    private LocalDate date;
    @JsonProperty("Cur_Abbreviation")
    private String currencyAbbreviation;
    @JsonProperty("Cur_Scale")
    private Double currencyScale;
    @JsonProperty("Cur_Name")
    private String currencyName;
    @JsonProperty("Cur_OfficialRate")
    private Double currencyOfficialRate;

    public Integer getCurrencyId() {
        return CurrencyId;
    }

    public CurrencyDto setCurrencyId(Integer currencyId) {
        CurrencyId = currencyId;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public CurrencyDto setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getCurrencyAbbreviation() {
        return currencyAbbreviation;
    }

    public CurrencyDto setCurrencyAbbreviation(String currencyAbbreviation) {
        this.currencyAbbreviation = currencyAbbreviation;
        return this;
    }

    public Double getCurrencyScale() {
        return currencyScale;
    }

    public CurrencyDto setCurrencyScale(Double currencyScale) {
        this.currencyScale = currencyScale;
        return this;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public CurrencyDto setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
        return this;
    }

    public Double getCurrencyOfficialRate() {
        return currencyOfficialRate;
    }

    public CurrencyDto setCurrencyOfficialRate(Double currencyOfficialRate) {
        this.currencyOfficialRate = currencyOfficialRate;
        return this;
    }
}
