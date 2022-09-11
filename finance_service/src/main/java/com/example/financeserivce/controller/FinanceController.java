package com.example.financeserivce.controller;

import com.example.financeserivce.controller.api.IFinanceController;
import com.example.financeserivce.dto.api.ICurrencyDto;
import com.example.financeserivce.enums.ECity;
import com.example.financeserivce.enums.EConvertCurrency;
import com.example.financeserivce.enums.ERatesCurrency;
import com.example.financeserivce.service.api.IFinanceService;
import com.example.financeserivce.service.pagination.Page;
import com.example.financeserivce.service.pagination.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.finance-controller.path}")
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
    @GetMapping("/rates/{currency}/city/{city}")//return best Rates based on cities
    public ResponseEntity<Page<ICurrencyDto>> getRates(@RequestParam(required = false, defaultValue = "1", name = "page") Integer page,
                                                       @RequestParam(required = false, defaultValue = "5", name = "size") Integer size,
                                                       @PathVariable String city, @PathVariable String currency) {
        return new ResponseEntity<>(service.getRates(PageRequest.of(page, size),
                                    ECity.valueOf(city.toUpperCase()),
                                    ERatesCurrency.valueOf(currency.toUpperCase())),
                                    HttpStatus.OK);
    }

    @Override
    @GetMapping("/rates")
    public ResponseEntity<Page<ICurrencyDto>> getNbrbRates(@RequestParam(required = false, defaultValue = "1", name = "page") Integer page,
                                                           @RequestParam(required = false, defaultValue = "5", name = "size") Integer size) {//return NBRB rates
        return new ResponseEntity<>(service.getNbrbRates(PageRequest.of(page, size)), HttpStatus.OK);
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
