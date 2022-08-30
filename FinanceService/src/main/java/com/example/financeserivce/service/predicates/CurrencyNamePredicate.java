package com.example.financeserivce.service.predicates;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class CurrencyNamePredicate implements Predicate<List<String>> {

    @Override
    public boolean test(List<String> list) {
        return !list.get(0).isEmpty() && list.get(0) != null;
    }
}
