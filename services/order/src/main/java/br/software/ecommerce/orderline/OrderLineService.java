package br.software.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderRepository;
    private final OrderLineMapper orderLineMapper;

    public UUID saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLine(orderLineRequest);
        return orderRepository.save(order).getId();
    }
}
