package com.example.midascodechallange.service;

import com.example.midascodechallange.repository.MarketRepository;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MarketServiceTest {

    @Mock
    private MarketRepository marketRepository;

    private MarketService marketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        marketService = new MarketService(marketRepository);
    }

    @Test
    public void testSyncMarketsFromApi() throws JSONException {
        marketService.syncMarketsFromApi();
    }
}






