package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int numcode;

    private String title;

    @OneToMany(
            mappedBy = "currency",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Orders> listOrders = new ArrayList<>();

    public Currency(){}
    public Currency(int numcode, String title ){
        this.numcode=numcode;
        this.title=title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(numcode, currency.numcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numcode);
    }

    @Override
    public String toString(){
        return (numcode+title);
    }
}
