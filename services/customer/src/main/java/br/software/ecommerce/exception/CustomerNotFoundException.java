package br.software.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNotFoundException extends RuntimeException {
    private final String message;

    public CustomerNotFoundException(String message) {
        this.message = message;
    }
}
