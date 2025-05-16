package com.perfulandia.proveedores.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_proveedor;

    @Column(length = 45, nullable = false)
    private String nombre_proveedor;

    @Column(precision = 10, nullable = false)
    private int fono_proveedor;

    @Column(length = 40)
    private String email_proveedor;

}
