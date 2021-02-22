package com.example.springsession.service.Impl;

import com.example.springsession.dto.ProductResponseDTO;
import com.example.springsession.dto.SearchResponseDTO;
import com.example.springsession.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SearchServiceImpl implements SearchService {

    @Override
    public SearchResponseDTO getProducts(String searchTerm, SearchResponseDTO request) {

        SearchResponseDTO responseDTO = new SearchResponseDTO();
        ProductResponseDTO product1 = new ProductResponseDTO();
        product1.setDescription("Samsung M51 is a mobile");
        product1.setTitle("Samsung M51");
        product1.setInStock(true);
        product1.setSalesPrice(27000);
        //responseDTO.setProducts(Arrays.asList(product1));

        ProductResponseDTO product2 = new ProductResponseDTO();
        product2.setDescription("Apple iPhone11 is a mobile");
        product2.setTitle("iPhone11");
        product2.setInStock(false);
        product2.setSalesPrice(80000);
        //responseDTO.setProducts(Arrays.asList(product2));

        ProductResponseDTO[] products = {product1,product2};

        responseDTO.setProducts(Arrays.asList(products));


        return responseDTO;
    }
}
