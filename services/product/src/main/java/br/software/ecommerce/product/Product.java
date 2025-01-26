package br.software.ecommerce.product;

import br.software.ecommerce.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private double availableQuantity;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
