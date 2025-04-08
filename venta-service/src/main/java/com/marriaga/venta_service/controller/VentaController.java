package com.marriaga.venta_service.controller;

import com.marriaga.venta_service.dto.DatosCompraDTO;
import com.marriaga.venta_service.service.IVentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private IVentaService service;

    @PostMapping("/crear")
    public ResponseEntity crearVenta(@RequestBody @Valid DatosCompraDTO datosCompraDTO) {
        service.realizarCompra(datosCompraDTO);
        return ResponseEntity.ok("Venta exitosa!");
    }

    @GetMapping("/mostrar")
    public ResponseEntity mostrarVentas() {
        return ResponseEntity.ok(service.mostrarVentas());
    }

    @GetMapping("/mostrar/{id}")
    public ResponseEntity mostrarVentaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.mostrarVentaPorId(id));
    }
}
