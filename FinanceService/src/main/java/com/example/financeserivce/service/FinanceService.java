package com.example.financeserivce.service;

import com.example.financeserivce.dto.api.ICurrencyDto;
import com.example.financeserivce.enums.ECity;
import com.example.financeserivce.enums.EConvertCurrency;
import com.example.financeserivce.enums.ERatesCurrency;
import com.example.financeserivce.service.api.IFinanceService;
import com.example.financeserivce.service.api.IWebService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceService implements IFinanceService {
    private final IWebService webService;

    public FinanceService(IWebService webService) {
        this.webService = webService;
    }

    @Override
    public ECity[] getCities() {
        return ECity.values();
    }

    @Override
    public EConvertCurrency[] getConvertCurrencies() {
        return EConvertCurrency.values();
    }

    @Override
    public ERatesCurrency[] getRatesCurrencies() {
        return ERatesCurrency.values();
    }

    @Override
    public List<ICurrencyDto> getRates(ECity city, ERatesCurrency currency) {
        return webService.getMyfinRates(city, currency);
    }

    @Override
    public List<ICurrencyDto> getNbrbRates() {
        return webService.getMyfinRates(ECity.NBRB, null);
    }

    @Override
    public Double getConversionRatio(EConvertCurrency original, EConvertCurrency target) {
        return webService.getConversionRatio(original, target);
    }
}
