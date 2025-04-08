package com.marriaga.producto_service.service;

import com.marriaga.producto_service.dto.DatosEditarProductoDTO;
import com.marriaga.producto_service.dto.DatosProductoDTO;
import com.marriaga.producto_service.dto.RespuestaProductoDTO;

import java.util.List;

public interface IProductoService {

    void createProducto(DatosProductoDTO datosProductoDTO);

    List<RespuestaProductoDTO> getProductos();

    RespuestaProductoDTO getProductoByCodigo(String codigo);

    RespuestaProductoDTO editProductoByCodigo(String codigo, DatosEditarProductoDTO datosProductoDTO);

    void deleteProductoByCodigo(String codigo);
}
