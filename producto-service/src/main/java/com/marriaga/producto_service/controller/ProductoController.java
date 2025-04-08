package com.marriaga.producto_service.controller;

import com.marriaga.producto_service.dto.DatosEditarProductoDTO;
import com.marriaga.producto_service.dto.DatosProductoDTO;
import com.marriaga.producto_service.service.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService service;

    @PostMapping("/crear")
    public ResponseEntity crearProducto(@RequestBody @Valid DatosProductoDTO datos) {
        service.createProducto(datos);
        return ResponseEntity.ok("Producto creado correctamente");
    }

    @GetMapping("/mostrar")
    public ResponseEntity mostrarProductos() {
        return ResponseEntity.ok(service.getProductos());
    }

    @GetMapping("/mostrar/{codigo}")
    public ResponseEntity mostrarProductoPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(service.getProductoByCodigo(codigo));
    }

    @PutMapping("/editar/{codigo}")
    public ResponseEntity editarProductoPorCodigo(@PathVariable String codigo,
                                                  @RequestBody DatosEditarProductoDTO datos) {
        return ResponseEntity.ok(service.editProductoByCodigo(codigo, datos));
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity eliminarProductoPorCodigo(@PathVariable String codigo){
        service.deleteProductoByCodigo(codigo);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }
}
