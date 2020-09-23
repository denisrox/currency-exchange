package com.example.demo.repositories;

import com.example.demo.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IOrdersRepository extends JpaRepository<Orders, Integer> {

}