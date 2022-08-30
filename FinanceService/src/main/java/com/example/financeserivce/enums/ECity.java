package com.example.financeserivce.enums;

public enum ECity {
    MINSK("currency/minsk"), GOMEL("currency/gomel"), BREST("currency/brest"),
    VITEBSK("currency/vitebsk"), MOGILEV("currency/mogilev"), GRODNO("currency-old/grodno"),
    NBRB("bank/kursy_valjut_nbrb");

    private final String url;

    ECity(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
