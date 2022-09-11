package org.example.telegramBot;

import java.util.*;

//Составить таблицу значений функции y=(x5+7x-1)/4 на отрезке [a; 
//b] с шагом h.
public class Test {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        double a = scn.nextDouble();
        double b = scn.nextDouble();

        double h = scn.nextDouble();

        Map<Double, Double> valuesMap = new HashMap<>();

        for (double i = a; i < b; i+=h) {
            valuesMap.put(i, (5*i+7*i-1)/4);//key = x, y = value
        }

        System.out.println(valuesMap);
    }
}
