package com.marriaga.producto_service.repository;

import com.marriaga.producto_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    Producto findByCodigoAndEstado(String codigo, boolean estado);

    List<Producto> findAllByEstado(Boolean estado);
}
