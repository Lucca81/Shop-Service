package dev.java.ecommerce.shopservice.controller;

import dev.java.ecommerce.shopservice.client.response.PlatziProductResponse;
import dev.java.ecommerce.shopservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;



    @GetMapping
    public ResponseEntity<List<PlatziProductResponse>> findAll(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatziProductResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductsById(id));
    }
}
