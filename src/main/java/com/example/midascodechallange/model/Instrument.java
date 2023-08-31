package com.example.midascodechallange.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "instrument")
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String symbol;

    // Constructors, getters, setters, etc.
}
