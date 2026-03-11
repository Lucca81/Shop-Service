package dev.java.ecommerce.shopservice.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
public class Product {


    private  Long id;

    private String title;

    private BigDecimal price;

    private Integer quantity;
}
