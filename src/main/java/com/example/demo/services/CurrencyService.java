package com.example.demo.services;

import com.example.demo.entities.Currency;
import com.example.demo.repositories.ICurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CurrencyService {

    @Autowired
    private ICurrencyRepository iCurrencyRepository;

    public Currency findOneById(int id){
        Currency currency = iCurrencyRepository.getOne(id);//.orElseThrow(() -> new Exception(""));
        return currency;//currency;
    }
    public List<Currency> findAll(){

        return iCurrencyRepository.findAll();//new ArrayList<Currency>();
        //iCurrencyRepository.findAll();
        //return ICurrencyRepository.findAll();
    }
    public int getCost(){
        //iCurrencyRepository.();
        return 0;

    }

    public Currency getOneByNumcode(int numcode){
        Currency currency = iCurrencyRepository.findByNumcode(numcode);
        /*if(currency == null){
            currency = new Currency();
            currency.setNumcode(Integer.parseInt(nodeList.item(0).getChildNodes().item(x).getChildNodes().item(1).getTextContent()));
            currency.setTitle((nodeList.item(0).getChildNodes().item(x).getChildNodes().item(1).getTextContent()));

        }*/
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
