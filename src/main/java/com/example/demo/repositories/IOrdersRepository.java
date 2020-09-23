package com.example.demo.repositories;

import com.example.demo.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface IOrdersRepository extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT value FROM orders o WHERE o.dates_id= ?1 and o.currency_id= ?2", nativeQuery = true)
    float getOrdersByDatesIdAndCurrencyId(int datesId, int currencyId);//int currencyId,int datesId);





    /*@Query("select c from Child c join fetch c.parent where c.id = :id")
    Child findByIdFetchParent(@Param("id") Long id);*/
}