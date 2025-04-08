package com.marriaga.producto_service.dto;

public record RespuestaProductoDTO(
        String codigo,
        String nombre,
        String marca,
        Double precio
) {
}
