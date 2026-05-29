package com.dvldigital.inventario.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(nullable = false, length = 255)
    private String contraseña;
    
    @Column(nullable = false, length = 1)
    private String rol; // a=admin, e=empleado, c=cliente
    
    @Column(nullable = false, length = 1)
    private String activo; // 1=activo, 0=inactivo
    
    @Column(name = "fecha_creacion", nullable = false)
    private java.time.LocalDate fechaCreacion;
}