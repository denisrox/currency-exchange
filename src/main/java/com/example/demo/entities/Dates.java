package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Data
@Entity
public class Dates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date daterequest;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "orderss", // название таблицы
            joinColumns = @JoinColumn(name = "dateid"),  // то что связываем
            inverseJoinColumns = @JoinColumn(name = "currencyid") // то на что связываем
    )
    private List<Currency> currencyList;

    @Override
    public String toString() {

        String allCurrency = "";
        for (Currency o : currencyList) {
            allCurrency += o.getTitle() + " ";
        }
        return "Dates [id:" + id+" size:" +currencyList.size()+ " " + daterequest + " " + allCurrency + "]";
    }
}
