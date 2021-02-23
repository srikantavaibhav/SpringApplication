package com.example.springsession.dto;

import java.util.List;

public class SearchResponseDTO {

    private List<ProductResponseDTO> products;

    private List<ProductResponseDTO> locationBaseProducts;

    public List<ProductResponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDTO> products) {
        this.products = products;
    }

    public List<ProductResponseDTO> getLocationBaseProducts() {
        return locationBaseProducts;
    }

    public void setLocationBaseProducts(List<ProductResponseDTO> locationBaseProducts) {
        this.locationBaseProducts = locationBaseProducts;
    }

}
