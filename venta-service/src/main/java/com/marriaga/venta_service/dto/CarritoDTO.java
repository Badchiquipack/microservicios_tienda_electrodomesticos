package com.marriaga.venta_service.dto;

import java.util.List;

public record CarritoDTO(
        Long idCarrito,
        List<ProductoDTO> productos,
        Double total
) {
}
