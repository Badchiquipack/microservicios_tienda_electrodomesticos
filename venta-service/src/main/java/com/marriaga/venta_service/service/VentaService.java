package com.marriaga.venta_service.service;

import com.marriaga.venta_service.dto.DatosCompraDTO;
import com.marriaga.venta_service.dto.RespuestaVentaDTO;
import com.marriaga.venta_service.infra.excepciones.ValidacionException;
import com.marriaga.venta_service.model.Venta;
import com.marriaga.venta_service.repository.ICarritoAPI;
import com.marriaga.venta_service.repository.IVentaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository repository;

    @Autowired
    private ICarritoAPI carritoAPI;

    @Override
    @Transactional
    @CircuitBreaker(name = "carrito-service", fallbackMethod = "fallbackRealizarCompra")
    @Retry(name = "carrito-service")
    public void realizarCompra(DatosCompraDTO datosCompraDTO) {
        carritoAPI.comprarCarrito(datosCompraDTO.idCarrito());
        Venta ventaNueva = new Venta(null, LocalDate.now(), datosCompraDTO.idCarrito());
        repository.save(ventaNueva);
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "carrito-service", fallbackMethod = "fallbackMostrarVentas")
    @Retry(name = "carrito-service")
    public List<RespuestaVentaDTO> mostrarVentas() {
        List<RespuestaVentaDTO> ventas = repository.findAll().stream()
                .map(venta -> new RespuestaVentaDTO(venta.getIdVenta(), venta.getFecha(),
                        carritoAPI.obtenerCarritoPorId(venta.getIdCarrito()))).toList();
        return ventas;
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "carrito-service", fallbackMethod = "fallbackMostrarVentaPorId")
    @Retry(name = "carrito-service")
    public RespuestaVentaDTO mostrarVentaPorId(Long id) {
        if (repository.existsById(id)) {
            Venta venta = repository.findById(id).get();
            return new RespuestaVentaDTO(venta.getIdVenta(), venta.getFecha(),
                    carritoAPI.obtenerCarritoPorId(venta.getIdCarrito()));
        } else {
            throw new ValidacionException("La venta con el id proporcionado no existe");
        }
    }

    public void fallbackRealizarCompra(DatosCompraDTO datosCompraDTO, Throwable throwable) {
        throw new ValidacionException(throwable.getMessage());
    }

    public List<RespuestaVentaDTO> fallbackMostrarVentas(Throwable throwable) {
        return Arrays.asList(null, null, null, null, null);
    }

    public RespuestaVentaDTO fallbackMostrarVentaPorId(Long id, Throwable throwable){
        return new RespuestaVentaDTO(9999L, null, null);
    }
}
