package com.example.midascodechallange.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class InstrumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllInstruments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/instrument/getall"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetInstrumentBySymbol() throws Exception {
        String symbol = "AAPL";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/instrument/get/" + symbol))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}