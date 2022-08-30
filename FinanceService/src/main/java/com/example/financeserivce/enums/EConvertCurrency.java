package com.example.financeserivce.enums;

public enum EConvertCurrency {
    USD(431), EUR(451),RUB(456), BYN(0);

    private final int id;

    EConvertCurrency(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
