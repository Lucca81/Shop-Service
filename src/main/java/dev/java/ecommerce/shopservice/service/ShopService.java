package dev.java.ecommerce.shopservice.service;

import dev.java.ecommerce.shopservice.client.response.PlatziProductResponse;
import dev.java.ecommerce.shopservice.controller.request.PaymentRequest;
import dev.java.ecommerce.shopservice.controller.request.ShopRequest;
import dev.java.ecommerce.shopservice.entities.Product;
import dev.java.ecommerce.shopservice.entities.Shop;
import dev.java.ecommerce.shopservice.entities.Status;
import dev.java.ecommerce.shopservice.exceptions.BusinessException;
import dev.java.ecommerce.shopservice.exceptions.DataNotFoundException;
import dev.java.ecommerce.shopservice.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ShopService {

    private final ShopRepository shopRepository;
    private final ProductService productService;

    public Shop getShopById(String id) {
        return shopRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Shop not Found"));
    }

    public Shop createShop(ShopRequest request) {
        shopRepository.findByClientAndStatus(request.clientId(), Status.OPEN)
                .ifPresent(shop -> {
                    throw new BusinessException("There is already an open shop for this client");
                });

        List<Product> products = getProducts(request);


        Shop shop = Shop.builder()
                .client(request.clientId())
                .status(Status.OPEN)
                .products(products)
                .build();

        shop.calculateTotalPrice();
        return shopRepository.save(shop);
    }

    private @NonNull List<Product> getProducts(ShopRequest request) {
        List<Product> products = new ArrayList<>();
        return request.products().stream().map(productRequest -> {
                    PlatziProductResponse platziProductResponse = productService.getProductsById(productRequest.id());
                    return Product.builder()
                            .id(platziProductResponse.id())
                            .title(platziProductResponse.title())
                            .price(platziProductResponse.price())
                            .quantity(productRequest.quantity())
                            .build();
                })
                .toList();
    }

    public Shop updateShop(String shopId, ShopRequest request) {
        Shop savedShop = getShopById(shopId);

        List<Product> products = getProducts(request);

        savedShop.setProducts(products);

        savedShop.calculateTotalPrice();
        return shopRepository.save(savedShop);

    }

    public Shop payShop(String shopId, PaymentRequest request) {
        Shop savedShop = getShopById(shopId);
        savedShop.setPaymentMethod(request.getPaymentMethod());
        savedShop.setStatus(Status.SOLD);
        return shopRepository.save(savedShop);
    }

    public void deleteShop(String id) {
        shopRepository.delete(getShopById(id));
    }
}
