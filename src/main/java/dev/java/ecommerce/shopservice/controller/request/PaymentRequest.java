package dev.java.ecommerce.shopservice.controller.request;

import dev.java.ecommerce.shopservice.entities.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private PaymentMethod paymentMethod;
}
