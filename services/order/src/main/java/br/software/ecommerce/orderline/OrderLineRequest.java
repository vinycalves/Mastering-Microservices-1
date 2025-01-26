package br.software.ecommerce.orderline;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record OrderLineRequest(UUID id, UUID orderId, @NotNull(message = "Product is mandatory") UUID productId,
                               @Positive(message = "Quantity is mandatory") double quantity) {
}
