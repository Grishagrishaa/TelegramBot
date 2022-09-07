package com.example.financeserivce.controller.api;

import com.example.financeserivce.dto.api.ICurrencyDto;
import com.example.financeserivce.enums.ECity;
import com.example.financeserivce.enums.EConvertCurrency;
import com.example.financeserivce.enums.ERatesCurrency;
import com.example.financeserivce.service.pagination.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFinanceController {
     ResponseEntity<ECity[]> getCities();//отдаешь енамы values(), в боте енамы, в базе юзеров тоже


     ResponseEntity<ERatesCurrency[]> getRatesCurrencies();//отдаешь енамы values()

     ResponseEntity<EConvertCurrency[]> getConvertCurrencies();//отдаешь енамы values()

     ResponseEntity<Page<ICurrencyDto>> getNbrbRates(Integer page, Integer size);

     ResponseEntity<Page<ICurrencyDto>> getRates(Integer page, Integer size, String city, String currency);

     ResponseEntity<Double> getConversionRatio(String original, String target);


}
