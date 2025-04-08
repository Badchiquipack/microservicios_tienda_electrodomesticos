package com.marriaga.venta_service.repository;

import com.marriaga.venta_service.dto.CarritoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "carrito-service")
public interface ICarritoAPI {

    @PutMapping("/carritos/comprar/{id}")
    void comprarCarrito(@PathVariable Long id);

    @GetMapping("/carritos/mostrar/{id}")
    CarritoDTO obtenerCarritoPorId(@PathVariable Long id);

}
