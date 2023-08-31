package com.example.midascodechallange.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "market")
public class Market {

    @Id
    private long id;

    private String code;
    private String symbol;
    private String name;
    private String country;
    private String website;

}
