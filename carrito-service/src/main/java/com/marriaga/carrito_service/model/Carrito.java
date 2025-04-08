package com.marriaga.carrito_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Carrito")
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> productos;
    private Double total;
    @Enumerated(EnumType.STRING)
    private EstadoVenta estadoVenta;
}
