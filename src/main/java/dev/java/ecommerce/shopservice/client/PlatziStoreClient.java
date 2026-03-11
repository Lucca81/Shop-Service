package dev.java.ecommerce.shopservice.client;

import dev.java.ecommerce.shopservice.client.response.PlatziProductResponse;
import dev.java.ecommerce.shopservice.exceptions.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Repository
@FeignClient(name = "PlatziStoreClient", url = "${shop.client.platzi}",configuration = {CustomErrorDecoder.class})
public interface PlatziStoreClient {

    @GetMapping("/products")
    List<PlatziProductResponse> getAllProducts();

    @GetMapping("/products/{id}")
    PlatziProductResponse getProductsById(@PathVariable Long id);
}
