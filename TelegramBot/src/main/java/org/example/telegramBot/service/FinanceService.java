package org.example.telegramBot.service;


import org.example.telegramBot.service.impl.currency.CurrencyMyfinDto;
import org.example.telegramBot.service.impl.currency.CurrencyMyfinNbrbDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service("financeServiceBean")
public class FinanceService {//todo interface
    private final Logger logger = LoggerFactory.getLogger(FinanceService.class);
    private final WebClient webClient;

    public FinanceService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String[] getConvertCurrencies(){
        String[] currencies = null;
        try{
             currencies = webClient
                    .get()
                    .uri("conversion/currencies")
                    .retrieve().bodyToMono(String[].class).block();


        }catch (WebClientResponseException e){
            logger.error("WebClientResponseException - {}", e.getMessage());
            throw new IllegalArgumentException("GET CONVERT CURRENCIES");
        }
        return currencies;
    }

    public String[] getRatesCurrencies(){
        String[] currencies = null;
        try{
            currencies = webClient
                    .get()
                    .uri("rates/currencies")
                    .retrieve().bodyToMono(String[].class).block();


        }catch (WebClientResponseException e){
            logger.error("WebClientResponseException - {}", e.getMessage());
            throw new IllegalArgumentException("GET RATES CURRENCIES");
        }
        return currencies;
    }

    public String[] getCities(){
        String[] cities = null;
        try{
            cities = webClient
                    .get()
                    .uri("rates/cities")
                    .retrieve().bodyToMono(String[].class).block();


        }catch (WebClientResponseException e){
            logger.error("WebClientResponseException - {}", e.getMessage());
            throw new IllegalArgumentException("GET CITIES");
        }
        return cities;
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

    public CurrencyMyfinNbrbDto[] getBestNbrbRates(){
        CurrencyMyfinNbrbDto[] nbrbDtos = null;
        try {
            nbrbDtos = webClient
                    .get()
                    .uri("rates")
                    .retrieve()
                    .bodyToMono(CurrencyMyfinNbrbDto[].class)
                    .block();
        } catch (Exception e) {
            logger.error("WebClientResponseException - {}", e.getMessage());
            throw new IllegalArgumentException("GET NBRB RATES");
        }
        return nbrbDtos;
    }

    public CurrencyMyfinDto[] getBestRates(String city, String currency){
        CurrencyMyfinDto[] myfinDtos = null;
        try {
            myfinDtos = webClient
                    .get()
                    .uri(String.join("/", "rates",currency, "city", city))
                    .retrieve()
                    .bodyToMono(CurrencyMyfinDto[].class)
                    .block();
        } catch (Exception e) {
            logger.error("WebClientResponseException - {}", e.getMessage());
            throw new IllegalArgumentException("GET NBRB RATES");
        }
        return myfinDtos;
    }


}
