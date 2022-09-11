package com.example.financeserivce.dto;

import com.example.financeserivce.dto.api.ICurrencyDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class CurrencyNbrbDto implements ICurrencyDto {//TODO ADD SPRING VALIDATION
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
    private Double rate;

    public Integer getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        CurrencyId = currencyId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCurrencyAbbreviation() {
        return currencyAbbreviation;
    }

    public void setCurrencyAbbreviation(String currencyAbbreviation) {
        this.currencyAbbreviation = currencyAbbreviation;
    }

    public Double getCurrencyScale() {
        return currencyScale;
    }

    public void setCurrencyScale(Double currencyScale) {
        this.currencyScale = currencyScale;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
