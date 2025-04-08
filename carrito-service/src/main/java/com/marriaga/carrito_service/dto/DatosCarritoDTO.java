package com.marriaga.carrito_service.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosCarritoDTO (
        @NotNull
        List<String> productos
){
}
