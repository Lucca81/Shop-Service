package dev.java.ecommerce.shopservice.controller.request;

import lombok.Builder;


public record ProductRequest(Long id, Integer quantity) {
}
