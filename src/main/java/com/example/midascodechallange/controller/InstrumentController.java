package com.example.midascodechallange.controller;

import com.example.midascodechallange.service.InstrumentService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

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
}
