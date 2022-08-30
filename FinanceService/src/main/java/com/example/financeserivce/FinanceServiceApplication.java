package com.example.financeserivce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class FinanceServiceApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(FinanceServiceApplication.class, args);
//        Document doc = Jsoup.connect("https://myfin.by/currency/minsk")
//                .userAgent("Chrome/4.0.249.0 Safari/532.5")
//                .referrer("http://www.google.com")
//                .get();
//
//        List<String> currencyValues = Collections.synchronizedList(new ArrayList<>(5));
//        List<CurrencyMyfinDto> currencyDtoList = Collections.synchronizedList(new ArrayList<>());
//
//        //working except grodno
//        Elements trs = doc.getElementsByClass("c-currency-table__main-row c-currency-table__main-row--with-arrow");
//        trs.forEach(t ->  {
//            t.select("td").forEach(e -> currencyValues.add(e.text()));
//            currencyDtoList.add(CurrencyMyfinDto.Builder.create()
//                            .setBankName(currencyValues.get(0))
//                            .setCurrencyName("USD")
//                            .setBankBuy(Float.valueOf(currencyValues.get(1)))
//                            .setBankSell(Float.valueOf(currencyValues.get(2)))
//                            .build()
//                    );
//            currencyValues.clear();
//        });
//
////        Elements trs = doc.getElementsByAttributeValueStarting("class", "tr-tb");
////        trs.forEach(t ->  {
////            t.select("td").forEach(e -> currencyValues.add(e.text()));
////            currencyDtoList.add(CurrencyMyfinDto.Builder.create()
////                            .setBankName(currencyValues.get(0))
////                            .setCurrencyName("USD")
////                            .setBankBuy(Float.valueOf(currencyValues.get(1)))
////                            .setBankSell(Float.valueOf(currencyValues.get(2)))
////                            .build()
////                    );
////            currencyValues.clear();
////        });
////
//
//
//        currencyDtoList.forEach(System.out::println);
    }
}
