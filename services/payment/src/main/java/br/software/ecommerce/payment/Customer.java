package br.software.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public record Customer(UUID id, @NotNull(message = "First name is required!") String firstName,
                       @NotNull(message = "Last name is required!") String lastName,
                       @NotNull(message = "Email is required") @Email(message = "The customer email is not correctly formated") String email) {
}
