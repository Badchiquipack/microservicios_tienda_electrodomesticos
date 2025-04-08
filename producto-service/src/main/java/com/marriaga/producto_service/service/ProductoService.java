package com.marriaga.producto_service.service;

import com.marriaga.producto_service.dto.DatosEditarProductoDTO;
import com.marriaga.producto_service.dto.DatosProductoDTO;
import com.marriaga.producto_service.dto.RespuestaProductoDTO;
import com.marriaga.producto_service.infra.excepciones.ValidacionException;
import com.marriaga.producto_service.model.Producto;
import com.marriaga.producto_service.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository repository;

    @Override
    public void createProducto(DatosProductoDTO datosProductoDTO) {
        Producto productoExistente = repository.findByCodigoAndEstado(datosProductoDTO.codigo(), true);
        if (productoExistente == null) {
            Producto productoNuevo = new Producto(null, datosProductoDTO.codigo().toUpperCase(), datosProductoDTO.nombre(),
                    datosProductoDTO.marca(), datosProductoDTO.precio(), true);
            repository.save(productoNuevo);
        } else {
            throw new ValidacionException("El producto con código " + datosProductoDTO.codigo() +
                    " ya existe");
        }
    }

    @Override
    public List<RespuestaProductoDTO> getProductos() {
        return repository.findAllByEstado(true).stream().map(producto -> new RespuestaProductoDTO(
                producto.getCodigo(), producto.getNombre(), producto.getMarca(), producto.getPrecio()
        )).toList();
    }

    @Override
    public RespuestaProductoDTO getProductoByCodigo(String codigo) {
        Producto producto = repository.findByCodigoAndEstado(codigo.toUpperCase(), true);
        if (producto!=null){
            return new RespuestaProductoDTO(producto.getCodigo(), producto.getNombre(),
                    producto.getMarca(), producto.getPrecio());
        }else {
            return null;
        }
    }

    @Override
    @Transactional
    public RespuestaProductoDTO editProductoByCodigo(String codigo, DatosEditarProductoDTO datos) {

        Producto producto = repository.findByCodigoAndEstado(codigo.toUpperCase(), true);
        if (producto!=null){
            if (datos.codigo()!=null){
                producto.setCodigo(datos.codigo().toUpperCase());
            }
            if (datos.nombre()!=null){
                producto.setNombre(datos.nombre());
            }
            if (datos.marca()!=null){
                producto.setMarca(datos.marca());
            }
            if (datos.precio()!=null){
                producto.setPrecio(datos.precio());
            }

            repository.save(producto);

            return new RespuestaProductoDTO(producto.getCodigo(), producto.getNombre(),
                    producto.getMarca(), producto.getPrecio());

        }else {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteProductoByCodigo(String codigo) {
        Producto producto = repository.findByCodigoAndEstado(codigo.toUpperCase(), true);
        if (producto!=null){
            producto.setEstado(false);
            repository.save(producto);
        }
        else {
            throw new ValidacionException("El producto con el código que intentas eliminar no existe");
        }
    }
}
