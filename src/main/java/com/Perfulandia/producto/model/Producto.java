package com.Perfulandia.producto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="producto")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;

    @Column(length = 25, nullable = false)
    private String nombre_producto;

    @Column (length = 25, nullable = false)
    private String categoria_producto;

    @Column (precision = 10, nullable = false)
    private int stock_producto;

    @Column (length = 100)
    private String descripcion_producto;

}
