package com.example.financeserivce.controller;

import com.example.financeserivce.controller.api.IFinanceController;
import com.example.financeserivce.dto.api.ICurrencyDto;
import com.example.financeserivce.enums.ECity;
import com.example.financeserivce.enums.EConvertCurrency;
import com.example.financeserivce.enums.ERatesCurrency;
import com.example.financeserivce.service.api.IFinanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/finance")
public class FinanceController implements IFinanceController {
    private final IFinanceService service;

    public FinanceController(IFinanceService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/rates/cities")
    public ResponseEntity<ECity[]> getCities() {
        return new ResponseEntity<>(service.getCities(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/rates/currencies")
    public ResponseEntity<ERatesCurrency[]> getRatesCurrencies() {
        return new ResponseEntity<>(service.getRatesCurrencies(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/rates/{currency}/city/{city}/")
    public ResponseEntity<List<ICurrencyDto>> getRates(@PathVariable String city, @PathVariable String currency) {
        return new ResponseEntity<>(service.getRates(
                                    city == null ? ECity.MINSK : ECity.valueOf(city.toUpperCase()),
                                    currency == null ? ERatesCurrency.USD : ERatesCurrency.valueOf(currency.toUpperCase())),
                                    HttpStatus.OK);
    }

    @Override
    @GetMapping("/rates")
    public ResponseEntity<List<ICurrencyDto>> getNbrbRates() {
        return new ResponseEntity<>(service.getNbrbRates(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/conversion/currencies")
    public ResponseEntity<EConvertCurrency[]> getConvertCurrencies() {
        return new ResponseEntity<>(service.getConvertCurrencies(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/conversion/{original}/{target}")
    public ResponseEntity<Double> getConversionRatio(@PathVariable String original, @PathVariable String target) {
        return new ResponseEntity<>(service.getConversionRatio(
                                    EConvertCurrency.valueOf(original.toUpperCase()),
                                    EConvertCurrency.valueOf(target.toUpperCase())),
                                    HttpStatus.OK);
    }
}
