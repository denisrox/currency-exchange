package com.example.demo.repositories;

import com.example.demo.entities.Dates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDatesRepository extends JpaRepository<Dates, Integer> {
}
