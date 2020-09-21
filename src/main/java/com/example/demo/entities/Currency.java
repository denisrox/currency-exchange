package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int numcode;

    private String title;

    @ManyToMany
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "currencyid"),  // то что связываем
            inverseJoinColumns = @JoinColumn(name = "dateid") // то на что связываем
    )
    private List<Dates> dates;



}
