package br.software.ecommerce.order;

import java.util.UUID;

public record OrderResponse(
        UUID id,
        String reference,
        Double amount,
        PaymentMethod paymentMethod,
        UUID customerID
) {
}
