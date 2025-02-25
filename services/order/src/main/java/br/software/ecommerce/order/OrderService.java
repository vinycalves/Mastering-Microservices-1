package br.software.ecommerce.order;

import br.software.ecommerce.customer.CustomerClient;
import br.software.ecommerce.exception.BusinessException;
import br.software.ecommerce.kafka.OrderConfirmation;
import br.software.ecommerce.kafka.OrderProducer;
import br.software.ecommerce.orderline.OrderLineRequest;
import br.software.ecommerce.orderline.OrderLineService;
import br.software.ecommerce.product.ProductClient;
import br.software.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public UUID createOrder(OrderRequest request) {
        var customer = customerClient.findByCustomerId(request.customerId()).orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with customer id: " + request.customerId()));

        var purchaseProducts = productClient.purchaseProducts(request.products());

        var order = orderRepository.save(orderMapper.toOrder(request));

        for (PurchaseRequest requestProduct : request.products()) {
            orderLineService.saveOrderLine(new OrderLineRequest(null, order.getId(), requestProduct.productId(), requestProduct.quantity()));
        }

        orderProducer.sendOrderConfirmation(new OrderConfirmation(request.reference(), request.amount(), request.paymentMethod(), customer, purchaseProducts));

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::fromOrder).collect(Collectors.toList());
    }

    public OrderResponse findOrderById(UUID orderId) {
        return orderRepository.findById(orderId).map(orderMapper::fromOrder).orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %id", orderId)));
    }
}
