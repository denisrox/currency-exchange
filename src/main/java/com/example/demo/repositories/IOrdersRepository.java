package com.example.demo.repositories;

import com.example.demo.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface IOrdersRepository extends JpaRepository<Orders, Integer> {

}