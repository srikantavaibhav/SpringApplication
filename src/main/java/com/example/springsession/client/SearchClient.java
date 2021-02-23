package com.example.springsession.client;

import java.util.Map;
import java.util.Objects;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "search-client", url="10.177.68.77:8983") //talking with other micro-services
public interface SearchClient {
    /**
     * API Ref.
     * [GET] 10.177.68.77:8983/solr/productCollection/select?q=samsung+galaxy
     */
    @RequestMapping(method = RequestMethod.GET, path = "/solr/productCollection/select")
    Map<String, Object> getProducts(@RequestParam("q") String query);

}
