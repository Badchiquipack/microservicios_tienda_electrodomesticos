package com.marriaga.carrito_service.service;

import com.marriaga.carrito_service.dto.DatosCarritoDTO;
import com.marriaga.carrito_service.dto.ProductoDTO;
import com.marriaga.carrito_service.dto.RespuestaCarritoProductosDTO;
import com.marriaga.carrito_service.infra.excepciones.ValidacionException;
import com.marriaga.carrito_service.model.Carrito;
import com.marriaga.carrito_service.model.EstadoVenta;
import com.marriaga.carrito_service.repository.ICarritoRepository;
import com.marriaga.carrito_service.repository.IProductoAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService implements ICarritoService {

    @Autowired
    private ICarritoRepository repository;

    @Autowired
    private IProductoAPI productoAPI;

    @Override
    @Transactional
    public void crearCarrito(DatosCarritoDTO datosCarritoDTO) {
        //validar codigos de productos
        List<String> productosNuevos = datosCarritoDTO.productos().stream()
                .map(String::toUpperCase).toList();

        List<String> productosDisponibles = productoAPI.obtenerProductos().stream()
                .map(ProductoDTO::codigo).toList();

        List<String> noEncontrados = productosNuevos.stream()
                .filter(producto -> !productosDisponibles.contains(producto))
                .toList();

        if (noEncontrados.isEmpty()) {
            Carrito carrito = new Carrito();

            carrito.setProductos(datosCarritoDTO.productos());

            //obtener total
            Double total = productoAPI.obtenerProductos().stream()
                    .filter(productoDTO -> productosNuevos.contains(productoDTO.codigo()))
                    .map(ProductoDTO::precio).reduce(0.0, Double::sum);

            carrito.setTotal(total);
            carrito.setEstadoVenta(EstadoVenta.EN_ESPERA);

            repository.save(carrito);
        } else {
            throw new ValidacionException("Los siguientes productos no están disponibles: " + noEncontrados);
        }
    }

    @Override
    @Transactional
    public List<RespuestaCarritoProductosDTO> mostrarCarritos() {

        List<RespuestaCarritoProductosDTO> respuesta = repository.findAll().stream().map(carrito -> {
            List<ProductoDTO> productos = productoAPI.obtenerProductos().stream()
                    .filter(productoDTO -> carrito.getProductos()
                            .contains(productoDTO.codigo())).toList();

            return new RespuestaCarritoProductosDTO(carrito.getIdCarrito(),
                    productos, carrito.getTotal(), carrito.getEstadoVenta());
        }).toList();

        return respuesta;
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "producto-service", fallbackMethod = "fallbackMostrarCarritoPorId")
    @Retry(name = "producto-service")
    public RespuestaCarritoProductosDTO mostrarCarritoPorId(Long idCarrito) {
        Optional<Carrito> carrito = repository.findById(idCarrito);
        if (carrito.isPresent()) {
            Carrito carritoExistente = carrito.get();

            List<ProductoDTO> productos = productoAPI.obtenerProductos().stream()
                    .filter(producto -> carritoExistente.getProductos()
                            .contains(producto.codigo())).toList();

            return new RespuestaCarritoProductosDTO(carritoExistente.getIdCarrito(), productos,
                    carritoExistente.getTotal(), carritoExistente.getEstadoVenta());
        } else {
            throw new ValidacionException("El carrito con el id proporcionado no existe");
        }
    }

    @Override
    @Transactional
    public RespuestaCarritoProductosDTO editarCarrito(Long idCarrito, DatosCarritoDTO datosCarritoDTO) {
        Optional<Carrito> carrito = repository.findById(idCarrito);
        if (carrito.isPresent()) {
            Carrito carritoExistente = carrito.get();

            if (carritoExistente.getEstadoVenta().equals(EstadoVenta.VENDIDO)) {
                throw new ValidacionException("El carrito que deseas " +
                        "editar ya fue vendido por lo cual no puede ser editado");
            } else {
                if (datosCarritoDTO.productos() != null) {
                    //validar codigos de productos
                    List<String> productosNuevos = datosCarritoDTO.productos().stream()
                            .map(String::toUpperCase).toList();

                    List<String> productosDisponibles = productoAPI.obtenerProductos().stream()
                            .map(ProductoDTO::codigo).toList();

                    List<String> noEncontrados = productosNuevos.stream()
                            .filter(producto -> !productosDisponibles.contains(producto))
                            .toList();

                    if (noEncontrados.isEmpty()) {

                        carritoExistente.setProductos(datosCarritoDTO.productos());

                        //obtener total
                        Double total = productoAPI.obtenerProductos().stream()
                                .filter(productoDTO -> productosNuevos.contains(productoDTO.codigo()))
                                .map(ProductoDTO::precio).reduce(0.0, Double::sum);

                        carritoExistente.setTotal(total);

                        repository.save(carritoExistente);
                    } else {
                        throw new ValidacionException("Los siguientes productos no están disponibles: " + noEncontrados);
                    }
                }

                List<ProductoDTO> productos = productoAPI.obtenerProductos().stream()
                        .filter(producto -> carritoExistente.getProductos()
                                .contains(producto.codigo())).toList();

                return new RespuestaCarritoProductosDTO(carritoExistente.getIdCarrito(), productos,
                        carritoExistente.getTotal(), carritoExistente.getEstadoVenta());
            }
        } else {
            throw new ValidacionException("El carrito con el id proporcionado no existe");
        }
    }

    @Override
    @Transactional
    public void eliminarCarrito(Long idCarrito) {
        if (repository.existsById(idCarrito) &&
                repository.findById(idCarrito).get().getEstadoVenta().equals(EstadoVenta.EN_ESPERA)) {
            repository.deleteById(idCarrito);
        } else {
            throw new ValidacionException("El carrito que deseas eliminar no existe o ya fue vendido");
        }
    }

    @Override
    @Transactional
    public void comprarCarrito(Long idCarrito) {
        if (repository.existsById(idCarrito)) {
            Carrito carrito = repository.findById(idCarrito).get();
            if (carrito.getEstadoVenta().equals(EstadoVenta.EN_ESPERA)) {
                carrito.setEstadoVenta(EstadoVenta.VENDIDO);
                repository.save(carrito);
            } else {
                throw new ValidacionException("El carrito que deseas vender ya fue vendido");
            }
        } else {
            throw new ValidacionException("El carrito con el id que deseas vender no existe");
        }
    }

    public RespuestaCarritoProductosDTO fallbackMostrarCarritoPorId(Long idCarrito, Throwable throwable){
        return new RespuestaCarritoProductosDTO(999999L, null, 0.0, null);
    }
}
