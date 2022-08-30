package com.example.financeserivce.controller.api;

import com.example.financeserivce.dto.api.ICurrencyDto;
import com.example.financeserivce.enums.ECity;
import com.example.financeserivce.enums.EConvertCurrency;
import com.example.financeserivce.enums.ERatesCurrency;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFinanceController {
     ResponseEntity<ECity[]> getCities();//отдаешь енамы values(), в боте енамы, в базе юзеров тоже


     ResponseEntity<ERatesCurrency[]> getRatesCurrencies();//отдаешь енамы values()

     ResponseEntity<EConvertCurrency[]> getConvertCurrencies();//отдаешь енамы values()

     ResponseEntity<List<ICurrencyDto>> getNbrbRates();

     ResponseEntity<List<ICurrencyDto>> getRates(String city, String currency);

     ResponseEntity<Double> getConversionRatio(String original, String target);


}
