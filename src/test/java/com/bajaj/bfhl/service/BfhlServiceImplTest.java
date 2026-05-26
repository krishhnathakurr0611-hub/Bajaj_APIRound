package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.RequestDto;
import com.bajaj.bfhl.dto.ResponseDto;
import com.bajaj.bfhl.service.impl.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BfhlServiceImplTest {

    private BfhlServiceImpl bfhlService;

    @BeforeEach
    public void setup() {
        bfhlService = new BfhlServiceImpl();
    }

    @Test
    public void testProcessData_ExampleA() {
        RequestDto request = new RequestDto();
        request.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        ResponseDto response = bfhlService.processData(request);

        assertTrue(response.isIsSuccess());
        assertEquals("krishna_thakur_06112005", response.getUserId());
        assertEquals("krishnathakur230625@acropolis.in", response.getEmail());
        assertEquals("0827CI231069", response.getRollNumber());

        assertEquals(Arrays.asList("1"), response.getOddNumbers());
        assertEquals(Arrays.asList("334", "4"), response.getEvenNumbers());
        assertEquals(Arrays.asList("A", "R"), response.getAlphabets());
        assertEquals(Arrays.asList("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    public void testProcessData_ExampleB() {
        RequestDto request = new RequestDto();
        request.setData(Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));

        ResponseDto response = bfhlService.processData(request);

        assertTrue(response.isIsSuccess());
        assertEquals(Arrays.asList("5"), response.getOddNumbers());
        assertEquals(Arrays.asList("2", "4", "92"), response.getEvenNumbers());
        assertEquals(Arrays.asList("A", "Y", "B"), response.getAlphabets());
        assertEquals(Arrays.asList("&", "-", "*"), response.getSpecialCharacters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    @Test
    public void testProcessData_ExampleC() {
        RequestDto request = new RequestDto();
        request.setData(Arrays.asList("A", "ABCD", "DOE"));

        ResponseDto response = bfhlService.processData(request);

        assertTrue(response.isIsSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals(Arrays.asList("A", "ABCD", "DOE"), response.getAlphabets());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }
}
