package br.software.ecommerce.orderline;

import br.software.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private UUID productId;
    private double quantity;
}
