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
    public SearchResponseDTO getProducts(String searchTerm, ProductRequestDTO request) {

        ProductRequestDTO myProductDetails[] = new ProductRequestDTO[10];

        Map<String, Object> productsResponse = searchClient.getProducts(request.getSearchTerm());
        Map<String,Object> response = (Map<String, Object>)productsResponse.get("response");
        List<Map<String, Object>> products = (List<Map<String, Object>>)response.get("docs");

        List<ProductResponseDTO> productDTOs = new ArrayList<>();
        for(int i=0;i<products.size();i++){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();

            String title = (String)products.get(i).get("name");
            productResponseDTO.setTitle(title);

            String description = (String)products.get(i).get("description");
            productResponseDTO.setDescription(description);

            int b = (int)products.get(i).get("isInStock");
            if(b==1)
            {
                productResponseDTO.setInStock(true);
            }
            else
            {
                productResponseDTO.setInStock(false);
            }

            double salePrice = (double)products.get(i).get("salePrice");
            productResponseDTO.setSalesPrice((int)salePrice);

            productDTOs.add(productResponseDTO);
        }


        SearchResponseDTO responseDTO = new SearchResponseDTO();
        responseDTO.setProducts(productDTOs);
        return responseDTO;
        /*
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

        ProductResponseDTO[] product1and2 = {product1,product2};

        responseDTO.setProducts(Arrays.asList(product1and2));


        return responseDTO;
        */
    }
}
