package com.perfurlandia.Sucursales.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "Sucursal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, length = 13, nullable = false)
    private String runEncargado;

    @Column(nullable = false)
    private String nombreEncargado;

    @Column(nullable = false)
    private String apellidoEncargado;

    @Column(nullable = false)
    private Date fechaInauguracion;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private Integer Telefono;





}
