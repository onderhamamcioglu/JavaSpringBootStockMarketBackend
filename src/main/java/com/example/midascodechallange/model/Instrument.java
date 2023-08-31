package com.example.midascodechallange.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "instrument")
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private String name;
    private String customName; // = "simple_name" in the Robinhood response

    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;

}
