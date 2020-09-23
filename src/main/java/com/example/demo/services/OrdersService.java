package com.example.demo.services;


import com.example.demo.component.CourseCbr;
import com.example.demo.repositories.IOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class OrdersService {

    @Autowired
    private IOrdersRepository iOrdersRepository;

    @Autowired
    private CourseCbr curseCbr;


}
