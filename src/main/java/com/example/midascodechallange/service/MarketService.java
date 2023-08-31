package com.example.midascodechallange.service;

import com.example.midascodechallange.model.Market;
import com.example.midascodechallange.repository.MarketRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class MarketService {

    private final MarketRepository marketRepository;

    @Autowired
    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public void syncMarketsFromApi() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.robinhood.com/markets";
        String response = restTemplate.getForObject(apiUrl, String.class);

        if (response != null) {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray results = jsonResponse.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject marketData = results.getJSONObject(i);

                Market market = new Market();
                market.setCode(marketData.getString("mic")); // code = mic field in Robinhood response
                market.setSymbol(marketData.getString("acronym")); // symbol = acronym field in Robinhood response
                market.setName(marketData.getString("name"));
                market.setCountry(marketData.getString("country"));
                market.setWebsite(marketData.getString("website"));

                marketRepository.save(market);
            }
        }
    }
}
