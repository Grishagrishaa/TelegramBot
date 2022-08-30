package com.example.financeserivce.service.api;

import com.example.financeserivce.dto.api.ICurrencyDto;
import com.example.financeserivce.enums.ECity;
import com.example.financeserivce.enums.EConvertCurrency;
import com.example.financeserivce.enums.ERatesCurrency;

import java.util.List;

public interface IFinanceService {
    ECity[] getCities();

    EConvertCurrency[] getConvertCurrencies();

    ERatesCurrency[] getRatesCurrencies();


    List<ICurrencyDto> getRates(ECity city, ERatesCurrency currency);

    List<ICurrencyDto> getNbrbRates();

    Double getConversionRatio(EConvertCurrency original, EConvertCurrency target);
}
