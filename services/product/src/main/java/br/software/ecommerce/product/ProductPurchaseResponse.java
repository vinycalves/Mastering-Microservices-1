package br.software.ecommerce.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductPurchaseResponse(
        UUID id,
        String name,
        String description,
        Double price,
        double availableQuantity
) {
}
