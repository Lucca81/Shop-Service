package dev.java.ecommerce.shopservice.service;

import dev.java.ecommerce.shopservice.client.response.PlatziProductResponse;
import dev.java.ecommerce.shopservice.controller.request.ShopRequest;
import dev.java.ecommerce.shopservice.entities.Product;
import dev.java.ecommerce.shopservice.entities.Shop;
import dev.java.ecommerce.shopservice.entities.Status;
import dev.java.ecommerce.shopservice.repository.ShopRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ShopService {

    private final ShopRepository shopRepository;
    private final ProductService productService;

    public Shop getShopById(String id){
        return shopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Shop not Found"));
    }

    public Shop createShop(ShopRequest request){

        shopRepository.findByClientAndStatus(request.clientId(), Status.OPEN)
                .ifPresent(shop -> {
                    throw new IllegalArgumentException("There is already an open basket for this client");
                });

        List<Product> products = new ArrayList<>();
        request.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse = productService.getProductsById(productRequest.id());
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());

        });


        Shop shop = Shop.builder()
                .client(request.clientId())
                .status(Status.OPEN)
                .products(products)
                .build();

        shop.calculateTotalPrice();
        return shopRepository.save(shop);
    }
}
