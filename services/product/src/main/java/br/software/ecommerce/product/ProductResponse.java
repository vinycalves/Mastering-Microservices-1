package br.software.ecommerce.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        Double price,
        double availableQuantity,
        UUID categoryId,
        String categoryName,
        String categoryDescription
) {
}
