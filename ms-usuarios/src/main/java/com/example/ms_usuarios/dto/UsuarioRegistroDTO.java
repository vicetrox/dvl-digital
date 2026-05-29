package com.example.ms_usuarios.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioRegistroDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "Mínimo 6 caracteres")
    private String password;
    
    @NotBlank(message = "El rol es requerido (ADMIN, CLIENTE, OPERADOR)")
    private String rol;
}