package com.example.demo.services;

import com.example.demo.entities.Currency;
import com.example.demo.repositories.ICurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CurrencyService {

    @Autowired
    private ICurrencyRepository iCurrencyRepository;


    public Currency getOneByNumcode(int numcode){
        Currency currency = iCurrencyRepository.findByNumcode(numcode);
        return currency;
    }
    public Currency createNewCurrency(int numCode, String title){
        Currency currency = new Currency();
        currency.setTitle(title);
        currency.setNumcode(numCode);
        iCurrencyRepository.save(currency);
        return currency;
    }

}
