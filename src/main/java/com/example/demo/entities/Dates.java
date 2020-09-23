package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Dates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date daterequest;

    @OneToMany(
            mappedBy = "dates",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Orders> listOrders=new ArrayList<>();

    public Dates(){};
    public Dates(Date daterequest){
        this.daterequest=daterequest;
    }

    public void addCurrency(Currency currency) {
        Orders orders = new Orders(this, currency);
        listOrders.add(orders);
        currency.getListOrders().add(orders);
    }

    @Override
    public String toString() {

        String allOrders = "";
        for (Orders o : listOrders) {
            allOrders += o.getValue() + " ";
        }
        return "Dates [id:" + id+" size:" +listOrders.size()+ " " + daterequest + " " + allOrders + "]";
    }
}
