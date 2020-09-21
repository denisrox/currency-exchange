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

    public Currency findOneById(Integer id){
        //Currency currency = ICurrencyRepository.findById(id).orElseThrow(() -> new Exception(""));
        return new Currency();//currency;
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

}
