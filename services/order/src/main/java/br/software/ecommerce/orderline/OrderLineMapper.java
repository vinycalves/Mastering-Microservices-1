package br.software.ecommerce.orderline;

import br.software.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder().id(orderLineRequest.id()).quantity(orderLineRequest.quantity()).order(Order.builder().id(orderLineRequest.orderId()).build()).build();
    }
}
