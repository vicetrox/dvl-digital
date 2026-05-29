package com.dvldigital.inventario.controller;

import com.dvldigital.inventario.model.entity.Usuario;
import com.dvldigital.inventario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable String id) {
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setActivo("1");
        usuario.setRol("c"); // cliente por defecto
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(usuario));
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String contraseña) {
        Optional<Usuario> usuario = usuarioService.obtenerPorEmail(email);
        if (usuario.isPresent() && usuario.get().getContraseña().equals(contraseña)) {
            return ResponseEntity.ok("login exitoso - token aqui (JWT)");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("credenciales invalidas");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable String id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioService.obtenerPorId(id);
        if (usuarioExistente.isPresent()) {
            usuario.setId(id);
            return ResponseEntity.ok(usuarioService.actualizar(usuario));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
