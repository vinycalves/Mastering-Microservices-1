package br.software.ecommerce.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Validated
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String houseNumber;
}
