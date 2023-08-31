package com.example.midascodechallange.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "market")
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;
    private String symbol;
    private String name;
    private String country;
    private String website;

}
