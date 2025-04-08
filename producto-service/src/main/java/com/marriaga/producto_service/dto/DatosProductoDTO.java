package com.marriaga.producto_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public record DatosProductoDTO(
        @NotBlank
        String codigo,
        @NotBlank
        String nombre,
        @NotBlank
        String marca,
        @NotNull
        Double precio
) {
}
