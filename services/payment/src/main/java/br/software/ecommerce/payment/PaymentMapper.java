package br.software.ecommerce.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest paymentRequest) {
        return new Payment().builder()
                .id(paymentRequest.id())
                .paymentMethod(paymentRequest.paymentMethod())
                .orderId(paymentRequest.orderId())
                .amount(paymentRequest.amount())
                .build();
    }
}
