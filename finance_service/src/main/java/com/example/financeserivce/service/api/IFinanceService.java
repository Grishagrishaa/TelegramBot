package com.example.financeserivce.service.api;

import com.example.financeserivce.dto.api.ICurrencyDto;
import com.example.financeserivce.enums.ECity;
import com.example.financeserivce.enums.EConvertCurrency;
import com.example.financeserivce.enums.ERatesCurrency;
import com.example.financeserivce.service.pagination.Page;
import com.example.financeserivce.service.pagination.PageRequest;

public interface IFinanceService {
    ECity[] getCities();

    EConvertCurrency[] getConvertCurrencies();

    ERatesCurrency[] getRatesCurrencies();


    Page<ICurrencyDto> getRates(PageRequest pageable, ECity city, ERatesCurrency currency);

    Page<ICurrencyDto> getNbrbRates(PageRequest pageable);

    Double getConversionRatio(EConvertCurrency original, EConvertCurrency target);
}
