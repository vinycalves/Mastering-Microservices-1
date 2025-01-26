package br.software.ecommerce.product;

import br.software.ecommerce.category.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequest(
        @NotNull(message = "The product name is required")
        String description,
        @NotNull(message = "The product description is required")
        String name,
        @PositiveOrZero(message = "The available quantity should be positive")
        double availableQuantity,
        @PositiveOrZero(message = "The price should be positive")
        Double price,
        @NotNull(message = "The product category is required")
        Category category
) {
}
