package org.example.telegramBot.service.financeService;


import org.example.telegramBot.service.financeService.api.IFinanceService;
import org.example.telegramBot.service.impl.financeService.dto.CurrencyMyfinDto;
import org.example.telegramBot.service.impl.financeService.dto.CurrencyMyfinNbrbDto;
import org.example.telegramBot.service.impl.financeService.pagination.Page;
import org.example.telegramBot.service.impl.financeService.pagination.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service("financeServiceBean")
public class FinanceService implements IFinanceService {
    private final Logger logger = LoggerFactory.getLogger(FinanceService.class);
    private final WebClient webClient;

    public FinanceService(WebClient webClient) {
        this.webClient = webClient;
    }
    @Override
    public String[] getConvertCurrencies(){
        String pathFragment = String.join("/", "conversion", "currencies");
        return getData(pathFragment);
    }

    @Override
    public String[] getRatesCurrencies(){
        String pathFragment = String.join("/", "rates", "currencies");
        return getData(pathFragment);
    }

    @Override
    public String[] getCities(){
        String pathFragment = String.join("/", "rates", "cities");
        return getData(pathFragment);
    }

    @Override
    public Page<CurrencyMyfinDto> getBestRates(String city, String currency, PageRequest pageable){
        ParameterizedTypeReference<Page<CurrencyMyfinDto>> typeReference = new ParameterizedTypeReference<Page<CurrencyMyfinDto>>() {};
        String[] pathFragments = new String[]{"rates", currency, "city", city};

        return getPage(typeReference, pathFragments, pageable);
    }

    @Override
    public Page<CurrencyMyfinNbrbDto> getBestNbrbRates(PageRequest pageable){
        ParameterizedTypeReference<Page<CurrencyMyfinNbrbDto>> typeReference = new ParameterizedTypeReference<Page<CurrencyMyfinNbrbDto>>() {};
        return getPage(typeReference, new String[]{"rates"}, pageable);
    }

    private <G> String[] getData(String pathFragment){
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

    @Override
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
