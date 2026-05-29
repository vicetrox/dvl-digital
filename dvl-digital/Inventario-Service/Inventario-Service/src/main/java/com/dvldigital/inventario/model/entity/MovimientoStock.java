package com.dvldigital.inventario.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimiento_stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoStock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @Column(nullable = false, length = 50)
    private String tipoMovimiento; // entrada, salida, devolucion, ajuste
    
    @Column(nullable = false)
    private Integer cantidad;
    
    @Column(nullable = false, length = 500)
    private String razon;
    
    @Column(name = "fecha_movimiento", nullable = false)
    private java.time.LocalDate fechaMovimiento;
}