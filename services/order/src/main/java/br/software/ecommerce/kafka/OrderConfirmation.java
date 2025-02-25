package br.software.ecommerce.kafka;

import br.software.ecommerce.customer.CustomerResponse;
import br.software.ecommerce.order.PaymentMethod;
import br.software.ecommerce.product.PurchaseResponse;

import java.util.List;

public record OrderConfirmation(
        String orderReference,
        Double totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse Response,
        List<PurchaseResponse> products
) {
}
