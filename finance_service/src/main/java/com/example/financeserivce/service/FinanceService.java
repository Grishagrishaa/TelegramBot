package com.example.financeserivce.service;

import com.example.financeserivce.dto.api.ICurrencyDto;
import com.example.financeserivce.enums.ECity;
import com.example.financeserivce.enums.EConvertCurrency;
import com.example.financeserivce.enums.ERatesCurrency;
import com.example.financeserivce.service.api.IFinanceService;
import com.example.financeserivce.service.api.IParseService;
import com.example.financeserivce.service.pagination.Page;
import com.example.financeserivce.service.pagination.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceService implements IFinanceService {
    private final IParseService webService;

    public FinanceService(IParseService webService) {
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
    public Page<ICurrencyDto> getRates(PageRequest pageable, ECity city, ERatesCurrency currency) {
        int pageNumber = pageable.getPage();
        int pageSize = pageable.getSize();

        List<ICurrencyDto> myfinRates = webService.getMyfinRates(city, currency);
        int from = (pageNumber - 1) * pageSize;
        int to = Math.min(from + pageSize, myfinRates.size());

        return Page.Builder.<ICurrencyDto>create()
                            .setContent(myfinRates.subList(from, to))
                            .setTotalElements(myfinRates.size())
                            .setSize(pageSize)
                            .setTotalPages(myfinRates.size() % pageSize == 0 ?  myfinRates.size() / pageSize : myfinRates.size() / pageSize + 1)
                            .build();
    }

    @Override
    public Page<ICurrencyDto> getNbrbRates(PageRequest pageable) {
        int pageNumber = pageable.getPage();
        int pageSize = pageable.getSize();

        List<ICurrencyDto> myfinRates = webService.getMyfinRates(ECity.NBRB, null);
        int from = (pageNumber - 1) * pageSize;
        int to = Math.min(from + pageSize, myfinRates.size());

        return Page.Builder.<ICurrencyDto>create()
                .setContent(myfinRates.subList(from, to))
                .setTotalElements(myfinRates.size())
                .setSize(pageSize)
                .setTotalPages(myfinRates.size() % pageSize == 0 ?  myfinRates.size() / pageSize : myfinRates.size() / pageSize + 1)
                .build();

    }

    @Override
    public Double getConversionRatio(EConvertCurrency original, EConvertCurrency target) {
        return webService.getConversionRatio(original, target);
    }
}
