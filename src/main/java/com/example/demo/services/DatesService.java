package com.example.demo.services;

import com.example.demo.entities.Currency;
import com.example.demo.entities.Dates;
import com.example.demo.repositories.ICurrencyRepository;
import com.example.demo.repositories.IDatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DatesService {
    @Autowired
    private IDatesRepository iDatesRepository;

    public Dates getOne(Integer id){
        return iDatesRepository.getOne(id);
    }
}
