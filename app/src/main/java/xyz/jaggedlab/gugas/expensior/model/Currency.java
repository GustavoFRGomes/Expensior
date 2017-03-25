package xyz.jaggedlab.gugas.expensior.model;

/**
 * Created by Asus on 24/03/2017.
 */

public enum Currency {
    EURO ("€"),
    DOLLAR_US ("$");

    Currency(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    private String currencySymbol;

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }
}
