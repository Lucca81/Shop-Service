package dev.java.ecommerce.shopservice.controller.request;

import lombok.Builder;

import java.util.List;

public record ShopRequest(Long clientId, List<ProductRequest> products) {
}
