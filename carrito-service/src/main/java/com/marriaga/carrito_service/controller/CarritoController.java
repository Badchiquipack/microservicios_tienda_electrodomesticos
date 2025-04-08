package com.marriaga.carrito_service.controller;

import com.marriaga.carrito_service.dto.DatosCarritoDTO;
import com.marriaga.carrito_service.service.ICarritoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carritos")
public class CarritoController {

    @Autowired
    private ICarritoService service;

    @PostMapping("/crear")
    public ResponseEntity crearCarrito(@RequestBody @Valid DatosCarritoDTO datosCarritoDTO) {
        service.crearCarrito(datosCarritoDTO);
        return ResponseEntity.ok("Carrito creado correctamente");
    }

    @GetMapping("/mostrar")
    public ResponseEntity mostrarCarritos() {
        return ResponseEntity.ok(service.mostrarCarritos());
    }

    @GetMapping("/mostrar/{id}")
    public ResponseEntity mostrarCarritoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.mostrarCarritoPorId(id));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity editarPorId(@PathVariable Long id,
                                      @RequestBody @Valid DatosCarritoDTO datosCarritoDTO) {
        return ResponseEntity.ok(service.editarCarrito(id, datosCarritoDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminarPorId(@PathVariable Long id) {
        service.eliminarCarrito(id);
        return ResponseEntity.ok("Carrito eliminado correctamente");
    }

    @PutMapping("/comprar/{id}")
    public ResponseEntity comprarCarrito(@PathVariable Long id) {
        service.comprarCarrito(id);
        return ResponseEntity.ok("Venta exitosa!");
    }
}
