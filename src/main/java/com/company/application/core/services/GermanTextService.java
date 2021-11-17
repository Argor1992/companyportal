package com.company.application.core.services;

import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

@Service
public class GermanTextService {
    public String getGermanDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(date);
    }

    public String getGermanCurrency(double amount) {
        Locale locale = new Locale("de", "DE");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(amount);
    }
}
