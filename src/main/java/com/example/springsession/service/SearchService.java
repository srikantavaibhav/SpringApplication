package com.example.springsession.service;

import com.example.springsession.dto.ProductRequestDTO;
import com.example.springsession.dto.SearchResponseDTO;

public interface SearchService {
    public SearchResponseDTO getProducts( ProductRequestDTO request);
}
