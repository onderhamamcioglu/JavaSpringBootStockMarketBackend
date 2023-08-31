package com.example.midascodechallange.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MarketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSyncMarketsFromApi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/market/sync"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}