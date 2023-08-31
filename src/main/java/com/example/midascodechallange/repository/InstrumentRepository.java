package com.example.midascodechallange.repository;


import com.example.midascodechallange.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    Instrument findBySymbol(String symbol);
}
