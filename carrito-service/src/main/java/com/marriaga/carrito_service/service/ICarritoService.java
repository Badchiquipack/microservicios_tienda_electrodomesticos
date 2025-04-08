package com.marriaga.carrito_service.service;

import com.marriaga.carrito_service.dto.DatosCarritoDTO;
import com.marriaga.carrito_service.dto.RespuestaCarritoProductosDTO;
import com.marriaga.carrito_service.model.Carrito;

import java.util.List;

public interface ICarritoService {

    void crearCarrito(DatosCarritoDTO datosCarritoDTO);

    List<RespuestaCarritoProductosDTO> mostrarCarritos();

    RespuestaCarritoProductosDTO mostrarCarritoPorId(Long idCarrito);

    RespuestaCarritoProductosDTO editarCarrito(Long idCarrito, DatosCarritoDTO datosCarritoDTO);

    void eliminarCarrito(Long idCarrito);

    void comprarCarrito(Long idCarrito);
}
