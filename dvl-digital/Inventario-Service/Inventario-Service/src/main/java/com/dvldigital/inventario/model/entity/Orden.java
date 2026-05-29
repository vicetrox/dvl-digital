package com.dvldigital.inventario.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "orden")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario_cliente", nullable = false)
    private Usuario usuarioCliente;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario_empleado", nullable = true)
    private Usuario usuarioEmpleado;
    
    @Column(nullable = false, length = 50)
    private String estado; // pendiente, confirmada, despachada, entregada, cancelada
    
    @Column(name = "fecha_creacion", nullable = false)
    private java.time.LocalDate fechaCreacion;
    
    @Column(name = "fecha_entrega", nullable = true)
    private java.time.LocalDate fechaEntrega;
    
    @Column(nullable = false)
    private Double total;
    
    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<DetalleOrden> detalles;
}