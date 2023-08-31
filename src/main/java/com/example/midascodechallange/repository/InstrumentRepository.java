package com.example.midascodechallange.repository;


import com.example.midascodechallange.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<Market, Long> {

}
