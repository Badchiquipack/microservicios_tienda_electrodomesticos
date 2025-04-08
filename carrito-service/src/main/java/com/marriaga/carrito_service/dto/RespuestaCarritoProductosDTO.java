package com.marriaga.carrito_service.dto;

import com.marriaga.carrito_service.model.EstadoVenta;

import java.util.List;

public record RespuestaCarritoProductosDTO (
        Long idCarrito,
        List<ProductoDTO> productos,
        Double total,
        EstadoVenta estadoVenta
){
}
