package com.marriaga.venta_service.dto;

import java.time.LocalDate;

public record RespuestaVentaDTO(
        Long idVenta,
        LocalDate fechaVenta,
        CarritoDTO carrito
) {
}
