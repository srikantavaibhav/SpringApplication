package com.example.springsession.service.Impl;

import com.example.springsession.client.SearchClient;
import com.example.springsession.dto.ProductRequestDTO;
import com.example.springsession.dto.SearchResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
class SearchServiceImplTest {

    @InjectMocks
    private SearchServiceImpl searchService;

    @Mock
    private SearchClient searchClient;

    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void getProducts() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> searchTermMockObject = objectMapper.readValue(new URL("file:src/test/resource/search.mock"), Map.class);

        Map<String, Object> locationMockObject = objectMapper.readValue(new URL("file:src/test/resource/location.mock"), Map.class);

        Mockito.when(searchClient.getProducts("Samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:Jakarta")).thenReturn(locationMockObject);


        ProductRequestDTO requestDTO = new ProductRequestDTO();
        requestDTO.setSearchTerm("Samsung");
        requestDTO.setStockLocation("Jakarta");
        SearchResponseDTO responseDTO = searchService.getProducts(requestDTO);


        assertEquals(responseDTO.getProducts().size(), 10);
        assertEquals(responseDTO.getLocationBaseProducts().size(), 10);

        Mockito.verify(searchClient).getProducts("Samsung");
        Mockito.verify(searchClient).getProducts("stockLocation:Jakarta");
    }

    @Test
    public void testGetProductsException() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> searchTermMockObject = objectMapper.readValue(
                new URL("file:src/test/resource/search.mock"), Map.class);


        Mockito.when(searchClient.getProducts("Samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:Jakarta"))
                .thenThrow(NullPointerException.class);

        ProductRequestDTO requestDTO = new ProductRequestDTO();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setStockLocation("jakarta");
        SearchResponseDTO response = searchService.getProducts(requestDTO);

        assertEquals(response.getProducts().size(), 10);
        assertEquals(response.getLocationBaseProducts(), null);

        Mockito.verify(searchClient).getProducts("Samsung");
        Mockito.verify(searchClient).getProducts("stockLocation:jakarta");

    }

}