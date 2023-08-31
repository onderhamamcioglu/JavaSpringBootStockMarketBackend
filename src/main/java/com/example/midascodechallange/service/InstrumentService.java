package com.example.midascodechallange.service;

import com.example.midascodechallange.model.Instrument;
import com.example.midascodechallange.model.Market;
import com.example.midascodechallange.repository.InstrumentRepository;
import com.example.midascodechallange.repository.MarketRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;
    private final MarketRepository marketRepository;
    @Autowired
    public InstrumentService(InstrumentRepository instrumentRepository, MarketRepository marketRepository) {
        this.instrumentRepository = instrumentRepository;
        this.marketRepository = marketRepository;
    }

    //TAKES TOO LONG TO MAKE API CALLS ONE BY ONE
    /*
    public void syncInstrumentsFromApi() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.robinhood.com/instruments/?symbol=";

        List<Instrument> instruments = instrumentRepository.findAll();

        for (Instrument instrument : instruments) {
            String symbol = instrument.getSymbol();
            String url = apiUrl + symbol;

            String response = restTemplate.getForObject(url, String.class);

            if (response != null) {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray results = jsonResponse.getJSONArray("results");

                if (results.length() > 0) {
                    JSONObject instrumentData = results.getJSONObject(0);

                    instrument.setName(instrumentData.getString("name"));
                    instrument.setCustomName(instrumentData.getString("simple_name")); // "customName" = "simple_name" in the Robinhood response

                    String marketSymbol = instrumentData.optString("market", null);
                    if (marketSymbol != null) {
                        Market market = marketRepository.findBySymbol(marketSymbol);
                        instrument.setMarket(market);
                    }

                    instrumentRepository.save(instrument);
                }
            }
        }
    }
    */

    public void syncInstrumentsFromApi(int batchSize) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.robinhood.com/instruments/?symbol=";

        List<Instrument> instruments = instrumentRepository.findAll();

        ExecutorService executorService = Executors.newFixedThreadPool(batchSize);

        for (Instrument instrument : instruments) {
            executorService.submit(() -> {
                String symbol = instrument.getSymbol();
                String url = apiUrl + symbol;

                String response = restTemplate.getForObject(url, String.class);

                if (response != null) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray results = jsonResponse.getJSONArray("results");

                        if (results.length() > 0) {
                            JSONObject instrumentData = results.getJSONObject(0);

                            instrument.setName(instrumentData.getString("name"));
                            instrument.setCustomName(instrumentData.optString("simple_name", null));

                            String marketSymbol = instrumentData.optString("market", null);
                            if (marketSymbol != null) {
                                Market market = marketRepository.findBySymbol(marketSymbol);
                                instrument.setMarket(market);
                            }

                            instrumentRepository.save(instrument);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Instrument getInstrumentBySymbol(String symbol) {
        return instrumentRepository.findBySymbol(symbol);
    }
}
