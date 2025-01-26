package br.software.ecommerce.order;

import br.software.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

public record OrderRequest(
        UUID id,
        String reference,
        @Positive(message = "Order amount should be positive")
        Double amount,
        @NotNull(message = "The payment method should be precise")
        PaymentMethod paymentMethod,
        @NotNull(message = "The customer should be present")
        @NotBlank(message = "The customer should be present")
        @NotEmpty(message = "The customer should be present")
        UUID customerId,
        @NotEmpty(message = "You should purchase at least one product")
        List<PurchaseRequest> products
) {
}
