package com.example.financeserivce.service;

import com.example.financeserivce.dto.CurrencyMyfinDto;
import com.example.financeserivce.dto.CurrencyMyfinNbrbDto;
import com.example.financeserivce.dto.CurrencyNbrbDto;
import com.example.financeserivce.dto.api.ICurrencyDto;
import com.example.financeserivce.enums.ECity;
import com.example.financeserivce.enums.EConvertCurrency;
import com.example.financeserivce.enums.ERatesCurrency;
import com.example.financeserivce.service.api.IWebService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Service
public class WebService implements IWebService {
    @Value("${app.myfin.url}")
    private String BASE_MYFIN_URL;

    private final Logger logger = LoggerFactory.getLogger(WebService.class);
    private final WebClient webClient;
    private final Predicate<List<String>> nameTest;

    public WebService(WebClient webClient, Predicate<List<String>> nameTest) {
        this.webClient = webClient;
        this.nameTest = nameTest;
    }

    @Override
    public Double getConversionRatio(EConvertCurrency original, EConvertCurrency target) {
        return getRate(original) / getRate(target);
    }

    private double getRate(EConvertCurrency currency){
        if (currency == EConvertCurrency.BYN) {
            return 1;
        }
        CurrencyNbrbDto currencyNbrbDto = null;
        try{
            currencyNbrbDto = webClient
                    .get()
                    .uri(currency.getId().toString())
                    .retrieve().bodyToMono(CurrencyNbrbDto.class).block();//ЕСЛИ ЗАПИСЬ НЕ НАЙДЕНА -> Ловим ошибку



        }catch (WebClientResponseException e){
            logger.error("WebClientResponseException - {}", e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }

        return currencyNbrbDto.getRate() / currencyNbrbDto.getCurrencyScale();
    }

    @Override
    public List<ICurrencyDto> getMyfinRates(ECity city, ERatesCurrency currency) {

        Document doc = null;
        try {
            doc = Jsoup.connect(BASE_MYFIN_URL + city.getUrl())
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return parseCurrency(doc, city, currency);
    }

    private List<ICurrencyDto> parseCurrency(Document doc, ECity city, ERatesCurrency currency){
        List<String> currencyValues = Collections.synchronizedList(new ArrayList<>(5));
        List<ICurrencyDto> currencyDtoList = Collections.synchronizedList(new ArrayList<>());


        switch (city) {
            case NBRB:
                Elements trContainers = doc.select("tbody").select("tr");

                trContainers.forEach(t -> {
                    t.select("td").forEach(e -> currencyValues.add(e.text()));
                    currencyDtoList.add(mapCurrencyDto(currencyValues, city, currency));
                });

                return currencyDtoList;
            case GRODNO:
                Elements elements = doc.getElementsByAttributeValueStarting("class", "tr-tb");
                elements.forEach(t -> {
                    t.select("td").forEach(e -> currencyValues.add(e.text()));
                    if(nameTest.test(currencyValues)){
                        currencyDtoList.add(mapCurrencyDto(currencyValues, city, currency));
                    }

                    currencyValues.clear();
                });

                return currencyDtoList;
            default:
                Elements trs = doc.getElementsByClass("c-currency-table__main-row c-currency-table__main-row--with-arrow");
                trs.forEach(t ->  {
                    t.select("td").forEach(e -> currencyValues.add(e.text()));
                    currencyDtoList.add(mapCurrencyDto(currencyValues, city, currency)
                    );
                    currencyValues.clear();
                });
                return currencyDtoList;
        }
    }

    private ICurrencyDto mapCurrencyDto(List<String>currencyValues, ECity city, ERatesCurrency currency){
        if(city == ECity.NBRB){
            CurrencyMyfinNbrbDto dto = CurrencyMyfinNbrbDto.Builder
                    .create()
                    .setCurrencyName(currencyValues.get(0))
                    .setRate(Float.valueOf(currencyValues.get(1)))
                    .setCode(currencyValues.get(2))
                    .setScale(Integer.valueOf(currencyValues.get(3)))
                    .build();

            currencyValues.clear();
            return dto;
        }
        switch (currency){
            case USD:
                CurrencyMyfinDto usdDto = CurrencyMyfinDto.Builder.create()
                        .setBankName(currencyValues.get(0))
                        .setCurrencyName(currency.toString())
                        .setBankBuy(Float.valueOf(currencyValues.get(1)))
                        .setBankSell(Float.valueOf(currencyValues.get(2)))
                        .build();
                currencyValues.clear();
                return usdDto;
            case EUR:
                CurrencyMyfinDto eurDto = CurrencyMyfinDto.Builder.create()
                        .setBankName(currencyValues.get(0))
                        .setCurrencyName(currency.toString())
                        .setBankBuy(Float.valueOf(currencyValues.get(3)))
                        .setBankSell(Float.valueOf(currencyValues.get(4)))
                        .build();
                currencyValues.clear();
                return eurDto;
            case RUB:
                CurrencyMyfinDto rubDto = CurrencyMyfinDto.Builder.create()
                        .setBankName(currencyValues.get(0))
                        .setCurrencyName(currency.toString())
                        .setBankBuy(Float.valueOf(currencyValues.get(5)))
                        .setBankSell(Float.valueOf(currencyValues.get(6)))
                        .build();
                currencyValues.clear();
                return rubDto;
        }
        throw new IllegalArgumentException("NO CURRENCY FOUNDED");
    }
}
