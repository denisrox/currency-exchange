package com.example.demo.repositories;

import com.example.demo.entities.Dates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface IDatesRepository extends JpaRepository<Dates, Integer> {
    Dates findByDaterequest(Date date);
}
