package org.example.telegramBot.service;


import org.example.telegramBot.service.impl.financeService.CurrencyMyfinDto;
import org.example.telegramBot.service.impl.financeService.CurrencyMyfinNbrbDto;
import org.example.telegramBot.service.impl.financeService.api.ICurrencyDto;
import org.example.telegramBot.service.impl.financeService.pagination.Page;
import org.example.telegramBot.service.impl.financeService.pagination.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.ws.rs.core.UriBuilder;

@Service("financeServiceBean")
public class FinanceService {//todo interface
    private final Logger logger = LoggerFactory.getLogger(FinanceService.class);
    private final WebClient webClient;

    public FinanceService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String[] getConvertCurrencies(){
        String pathFragment = String.join("/", "conversion", "currencies");
        return getData(pathFragment);
    }

    public String[] getRatesCurrencies(){
        String pathFragment = String.join("/", "rates", "currencies");
        return getData(pathFragment);
    }

    public String[] getCities(){
        String pathFragment = String.join("/", "rates", "cities");
        return getData(pathFragment);
    }

    public Page<CurrencyMyfinDto> getBestRates(String city, String currency, PageRequest pageable){
        ParameterizedTypeReference<Page<CurrencyMyfinDto>> typeReference = new ParameterizedTypeReference<Page<CurrencyMyfinDto>>() {};
        String[] pathFragments = new String[]{"rates", currency, "city", city};

        return getPage(typeReference, pathFragments, pageable);
    }

    public Page<CurrencyMyfinNbrbDto> getBestNbrbRates(PageRequest pageable){
        ParameterizedTypeReference<Page<CurrencyMyfinNbrbDto>> typeReference = new ParameterizedTypeReference<Page<CurrencyMyfinNbrbDto>>() {};
        return getPage(typeReference, new String[]{"rates"}, pageable);
    }

    public <G> String[] getData(String pathFragment){
        try{
            return webClient
                    .get()
                    .uri(pathFragment)
                    .retrieve().bodyToMono(String[].class).block();


        }catch (WebClientResponseException e){
            logger.error("WebClientResponseException - {}", e.getMessage());
            throw new IllegalArgumentException("GET DATA");
        }
    }


    private <G> Page<G> getPage(ParameterizedTypeReference<Page<G>> typeReference, String[] pathFragments, PageRequest pageable){
        try {
            return webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .pathSegment(pathFragments).path("/")
                            .queryParam("page", pageable.getPage())
                            .queryParam("size", pageable.getSize())
                            .build())
                    .retrieve()
                    .bodyToMono(typeReference)
                    .block();
        } catch (WebClientResponseException e) {
            logger.error("WebClientResponseException - {}, URI - {}", e.getMessage(), e.getRequest().getURI());
            throw new IllegalArgumentException("GET NBRB RATES");
        }
    }

    public Double getRate(String original, String target){
        Double rate = null;
        try{
            rate = webClient
                    .get()
                    .uri(String.join("/","conversion", original, target))
                    .retrieve().bodyToMono(Double.class).block();


        }catch (WebClientResponseException e){
            logger.error("WebClientResponseException - {}", e.getMessage());
            throw new IllegalArgumentException("GET RATE");
        }
        return rate;
    }
}
