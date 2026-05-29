package com.dvldigital.inventario.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false, unique = true, length = 50)
    private String sku;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 500)
    private String descripcion;
    
    @Column(nullable = false, length = 50)
    private String categoria; // ram, gpu, etc
    
    @Column(nullable = false)
    private Double precio;
    
    @Column(nullable = false, length = 1)
    private String activo; // 1=activo, 0=inactivo
    
    @Column(name = "fecha_creacion", nullable = false)
    private java.time.LocalDate fechaCreacion;
}