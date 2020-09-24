package com.example.demo.entities;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;


@Data
@Entity
public class Orders {

    @EmbeddedId
    private OrdersId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("datesId")
    private Dates dates;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("currencyId")
    private Currency currency;

    private float value;

    public Orders(){}

    public Orders(Dates dates, Currency currency){
        this.dates=dates;
        this.currency=currency;
        this.id=new OrdersId(dates.getId(), currency.getId());
    }

    public Orders(Dates dates, Currency currency, float value){
        this.dates=dates;
        this.currency=currency;
        this.value=value;
        this.id=new OrdersId(dates.getId(), currency.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Orders that = (Orders) o;
        return Objects.equals(dates, that.dates) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dates, currency);
    }





}
