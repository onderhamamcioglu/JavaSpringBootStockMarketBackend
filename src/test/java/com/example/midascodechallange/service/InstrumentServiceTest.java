package com.example.midascodechallange.service;

import com.example.midascodechallange.model.Instrument;
import com.example.midascodechallange.repository.InstrumentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class InstrumentServiceTest {

    @Mock
    private InstrumentRepository instrumentRepository;

    @InjectMocks
    private InstrumentService instrumentService;

    @Test
    public void testGetAllInstruments() {
        Instrument instrument1 = new Instrument();
        instrument1.setId(1L);
        Instrument instrument2 = new Instrument();
        instrument2.setId(2L);

        List<Instrument> instruments = Arrays.asList(instrument1, instrument2);

        when(instrumentRepository.findAll()).thenReturn(instruments);

        List<Instrument> result = instrumentService.getAllInstruments();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
    }

    @Test
    public void testGetInstrumentBySymbol() {
        String symbol = "AAPL";
        Instrument instrument = new Instrument();
        instrument.setId(1L);
        instrument.setSymbol(symbol);

        when(instrumentRepository.findBySymbol(symbol)).thenReturn(instrument);

        Instrument result = instrumentService.getInstrumentBySymbol(symbol);

        assertEquals(1L, result.getId());
        assertEquals(symbol, result.getSymbol());
    }
}
