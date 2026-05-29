package com.dvldigital.inventario.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    
    @Column(nullable = false)
    private Integer cantidadDisponible;
    
    @Column(nullable = false)
    private Integer cantidadMinima;
    
    @Column(nullable = false)
    private Integer cantidadMaxima;
    
    @Column(nullable = false, length = 250)
    private String ubicacion; // bodega, pasillo, estante o igual se puede mas especifico
    
    @Column(name = "ultima_actualizacion", nullable = false)
    private java.time.LocalDateTime ultimaActualizacion;
}