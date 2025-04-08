package com.marriaga.venta_service.dto;

import jakarta.validation.constraints.NotNull;

public record DatosCompraDTO(
        @NotNull
        Long idCarrito
) {
}
