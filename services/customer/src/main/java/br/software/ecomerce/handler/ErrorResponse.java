package br.software.ecomerce.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
