package br.software.ecommerce.product;

import java.util.UUID;

public record PurchaseResponse(
        UUID id,
        String name,
        String description,
        Double price,
        double quantity
) {
}
