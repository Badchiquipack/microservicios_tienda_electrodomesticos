package com.marriaga.venta_service.dto;

public record ProductoDTO(
        String codigo,
        String nombre,
        String marca,
        Double precio
) {
}
