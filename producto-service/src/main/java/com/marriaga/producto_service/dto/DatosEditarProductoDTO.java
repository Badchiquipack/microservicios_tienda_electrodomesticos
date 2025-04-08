package com.marriaga.producto_service.dto;

public record DatosEditarProductoDTO(
        String codigo,
        String nombre,
        String marca,
        Double precio
) {
}
