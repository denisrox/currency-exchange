package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int numcode;

    private String title;

    @ManyToMany//(mappedBy = "currencyList")
    @JoinTable(
            name = "orderss",
            joinColumns = @JoinColumn(name = "currencyid"),  // то что связываем
            inverseJoinColumns = @JoinColumn(name = "dateid") // то на что связываем
    )
    private List<Dates> dates;



}
