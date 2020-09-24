package com.example.demo.services;


import com.example.demo.repositories.IOrdersRepository;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrdersService {

    @Autowired
    private IOrdersRepository iOrdersRepository;

    public float getValueById(Integer dateId,Integer currencyId){
        try {
            return iOrdersRepository.getOrdersByDatesIdAndCurrencyId(dateId,currencyId);
        }
        catch (AopInvocationException exceprion){
            return 0;
        }

    }


}
