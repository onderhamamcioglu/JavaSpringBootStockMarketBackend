package com.example.midascodechallange.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "instrument")
public class Instrument {
    @Id
    private long id;

    private String symbol;

    // Constructors, getters, setters, etc.
}
