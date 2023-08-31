package com.example.midascodechallange.controller;

import com.example.midascodechallange.service.MarketService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    private final MarketService marketService;

    @Autowired
    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncMarkets() {
        try {
            marketService.syncMarketsFromApi();
            return new ResponseEntity<>("Market data synced.", HttpStatus.OK);
        } catch (RestClientException | JSONException e) {
            return new ResponseEntity<>("Error syncing market data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
