package com.example.springsession.controller;


import com.example.springsession.dto.SearchResponseDTO;
import com.example.springsession.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {

    public ProductController(SearchService searchService) {
        this.searchService = searchService;
    }

   @Autowired
   private SearchService searchService;

    @PostMapping(path =  "/search/{searchTerm}")
    public SearchResponseDTO getProducts(@PathVariable String searchTerm, @RequestBody SearchResponseDTO request){
        return searchService.getProducts(searchTerm, request);

    }
}
