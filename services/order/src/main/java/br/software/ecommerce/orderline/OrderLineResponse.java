package br.software.ecommerce.orderline;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderLineResponse(
        UUID id,
        BigDecimal quantity
) {
}
