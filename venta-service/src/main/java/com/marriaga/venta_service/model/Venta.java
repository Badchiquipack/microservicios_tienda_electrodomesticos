package com.marriaga.venta_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Venta")
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    private LocalDate fecha;
    private Long idCarrito;
}
