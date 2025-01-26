package br.software.ecommerce.order;

import br.software.ecommerce.customer.CustomerClient;
import br.software.ecommerce.exception.BusinessException;
import br.software.ecommerce.orderline.OrderLineRequest;
import br.software.ecommerce.orderline.OrderLineService;
import br.software.ecommerce.product.ProductClient;
import br.software.ecommerce.product.PurchaseRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    public UUID createOrder(OrderRequest request) {
        var customer = customerClient.findByCustomerId(request.customerId()).orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with customer id: " + request.customerId()));

        productClient.purchaseProducts(request.products());

        var order = orderRepository.save(orderMapper.toOrder(request));

        for (PurchaseRequest requestProduct : request.products()) {
            orderLineService.saveOrderLine(new OrderLineRequest(null, order.getId(), requestProduct.productId(), requestProduct.quantity()));
        }

        return order.getId();
    }
}
