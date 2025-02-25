package br.software.ecommerce.orderline;

import java.util.UUID;

public record OrderLineResponse(
        UUID id,
        double quantity
) {
}
