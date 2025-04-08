package com.marriaga.carrito_service.repository;

import com.marriaga.carrito_service.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "producto-service")
public interface IProductoAPI {

    @GetMapping("/productos/mostrar")
    List<ProductoDTO> obtenerProductos();
}
