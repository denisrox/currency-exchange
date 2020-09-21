package com.example.demo.repositories;

import com.example.demo.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ICurrencyRepository extends JpaRepository<Currency, Integer> {
    List<Currency> findAll();
    //Currency findById();
}
