package com.example.ms_usuarios.controller;

import org.springframework.web.bind.annotation.*;

import com.example.ms_usuarios.dto.UsuarioRegistroDTO;
import com.example.ms_usuarios.model.Usuario;
import com.example.ms_usuarios.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @PostMapping("/registro")
    public Usuario registrar(@Valid @RequestBody UsuarioRegistroDTO dto) {
        return usuarioService.registrarUsuario(dto);
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }
}