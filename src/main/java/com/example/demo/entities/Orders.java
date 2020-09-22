package com.example.demo.entities;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer value;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User responsibleUser;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User responsibleUser;


}
