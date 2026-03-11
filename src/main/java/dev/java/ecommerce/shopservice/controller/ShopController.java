package dev.java.ecommerce.shopservice.controller;

import dev.java.ecommerce.shopservice.controller.request.PaymentRequest;
import dev.java.ecommerce.shopservice.controller.request.ShopRequest;
import dev.java.ecommerce.shopservice.entities.Shop;
import dev.java.ecommerce.shopservice.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable String id){
        return ResponseEntity.ok(shopService.getShopById(id));
    }

    @PostMapping
    public ResponseEntity<Shop> createShop(@RequestBody ShopRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(shopService.createShop(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable String id, @RequestBody ShopRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(shopService.updateShop(id, request));
    }

    @PutMapping("/{id}/payment")
    public ResponseEntity<Shop> payShop(@PathVariable String id, @RequestBody PaymentRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(shopService.payShop(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable String id){
        shopService.deleteShop(id);
        return ResponseEntity.noContent().build();
    }
}
