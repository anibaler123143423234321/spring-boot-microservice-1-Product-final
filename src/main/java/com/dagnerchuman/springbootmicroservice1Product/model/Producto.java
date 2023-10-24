package com.dagnerchuman.springbootmicroservice1Product.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import javax.validation.constraints.Min;

@Data
@Entity
@Table(name="producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "categoria_id", nullable = false)
    private Long categoriaId;

    @Column(name="foto", length = 1200, nullable = true )
    private String picture;

    @Column(name="precio", nullable = false)
    private Double precio;

    @Column(name="fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "negocio_id", nullable = false)
    private Long negocioId;


    @Column(name = "stock", nullable = false)
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;


}
