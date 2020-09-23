package com.example.demo.entities;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrdersId implements Serializable {
    @Column(name = "currencyId")
    private int currencyId;

    @Column(name = "datesId")
    private int datesId;

    private OrdersId(){};

    public OrdersId(int currencyId, int datesId){
        this.currencyId=currencyId;
        this.datesId=datesId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrdersId that = (OrdersId) o;
        return Objects.equals(datesId, that.datesId) &&
                Objects.equals(currencyId, that.currencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datesId, currencyId);
    }
}
