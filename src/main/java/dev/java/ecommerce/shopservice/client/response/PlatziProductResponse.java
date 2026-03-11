package dev.java.ecommerce.shopservice.client.response;

import java.io.Serializable;
import java.math.BigDecimal;

public record PlatziProductResponse(Long id, String title, BigDecimal price) implements Serializable {
}
