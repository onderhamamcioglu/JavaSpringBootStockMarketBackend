package com.example.midascodechallange.controller;

import com.example.midascodechallange.model.Instrument;
import com.example.midascodechallange.service.InstrumentService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequestMapping("/api/instrument")
public class InstrumentController {

    private final InstrumentService instrumentService;

    @Autowired
    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncInstruments() {
        try{
            instrumentService.syncInstrumentsFromApi(20); //instrumentService.syncInstrumentsFromApi();
            return new ResponseEntity<>("Instrument data updated.", HttpStatus.OK);
        } catch (RestClientException e) { //catch (RestClientException | JSONException e) {
            return new ResponseEntity<>("Error syncing instrument data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{symbol}")
    public ResponseEntity<Instrument> getInstrumentBySymbol(@PathVariable String symbol) {
        Instrument instrument = instrumentService.getInstrumentBySymbol(symbol);
        if (instrument != null) {
            return new ResponseEntity<>(instrument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Instrument>> getAllInstruments() {
        List<Instrument> instruments = instrumentService.getAllInstruments();
        if(instruments != null){
            return new ResponseEntity<>(instruments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
