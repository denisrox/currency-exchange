package com.example.demo.repositories;

import com.example.demo.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICurrencyRepository extends JpaRepository<Currency, Integer> {
    Currency findByNumcode(int numcode);
}
