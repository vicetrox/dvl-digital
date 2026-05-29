package com.duoc.pagos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ¡Esta es la clave! Aquí guardaremos el ID del pedido que se está pagando
    @Column(name = "pedido_id")
    private Long pedidoId; 

    private Double monto;

    // Para saber si el pago fue "APROBADO" o "RECHAZADO"
    private String estado; 
}