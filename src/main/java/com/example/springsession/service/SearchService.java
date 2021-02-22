package com.example.springsession.service;

import com.example.springsession.dto.SearchResponseDTO;

public interface SearchService {
    public SearchResponseDTO getProducts(String searchTerm, SearchResponseDTO request);
}
