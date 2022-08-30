package com.example.financeserivce.service.api;

import com.example.financeserivce.dto.api.ICurrencyDto;
import com.example.financeserivce.enums.ECity;
import com.example.financeserivce.enums.EConvertCurrency;
import com.example.financeserivce.enums.ERatesCurrency;

import java.util.List;

public interface IWebService {
    List<ICurrencyDto> getMyfinRates(ECity city, ERatesCurrency currency);

    Double getConversionRatio(EConvertCurrency original, EConvertCurrency target);
}
