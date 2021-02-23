package com.example.springsession.service.Impl;

import com.example.springsession.client.SearchClient;
import com.example.springsession.dto.ProductRequestDTO;
import com.example.springsession.dto.ProductResponseDTO;
import com.example.springsession.dto.SearchResponseDTO;
import com.example.springsession.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchClient searchClient;

    @Override
    public SearchResponseDTO getProducts( ProductRequestDTO request) {

        ProductResponseDTO myProductDetails[] = new ProductResponseDTO[10];
        ProductResponseDTO myProductDetailsLoc[] = new ProductResponseDTO[10];

        Map<String, Object> productsResponse = searchClient.getProducts(request.getSearchTerm());
        Map<String,Object> response = (Map<String, Object>)productsResponse.get("response");
        List<Map<String, Object>> products = (List<Map<String, Object>>)response.get("docs");

        Map<String, Object> productsResponseLoc = searchClient.getProducts("stockLocation"+request.getStockLocation());
        Map<String,Object> responseLoc = (Map<String, Object>)productsResponseLoc.get("response");
        List<Map<String, Object>> productsLoc = (List<Map<String, Object>>)responseLoc.get("docs");

        SearchResponseDTO responseDTO = new SearchResponseDTO();
        ProductResponseDTO[] product = new ProductResponseDTO[10];

        for(int i=0; i<10; i++) {
            myProductDetails[i] = new ProductResponseDTO();
            int stock = (int)products.get(i).get("isInStock");
            if(stock==1)
                myProductDetails[i].setInStock(true);
            else
                myProductDetails[i].setInStock(false);
            myProductDetails[i].setTitle((String) products.get(i).get("name"));
            myProductDetails[i].setSalesPrice((int)((double) products.get(i).get("salePrice")));
            myProductDetails[i].setDescription((String) products.get(i).get("description"));
        }
        for(int i=0; i<10; i++) {
            myProductDetailsLoc[i] = new ProductResponseDTO();
            int stock = (int)productsLoc.get(i).get("isInStock");
            if(stock==1)
                myProductDetailsLoc[i].setInStock(true);
            else
                myProductDetailsLoc[i].setInStock(false);
            myProductDetailsLoc[i].setTitle((String) productsLoc.get(i).get("name"));
            myProductDetailsLoc[i].setSalesPrice((int)((double) productsLoc.get(i).get("salePrice")));
            myProductDetailsLoc[i].setDescription((String) productsLoc.get(i).get("description"));
        }
        responseDTO.setProducts(Arrays.asList(myProductDetails));
        responseDTO.setLocationBaseProducts(Arrays.asList(myProductDetailsLoc));
        return responseDTO;
    }
}