package com.marriaga.venta_service.service;

import com.marriaga.venta_service.dto.CarritoDTO;
import com.marriaga.venta_service.dto.DatosCompraDTO;
import com.marriaga.venta_service.dto.RespuestaVentaDTO;

import java.util.List;

public interface IVentaService {

    void realizarCompra(DatosCompraDTO datosCompraDTO);

    List<RespuestaVentaDTO> mostrarVentas();

    RespuestaVentaDTO mostrarVentaPorId(Long id);
}
