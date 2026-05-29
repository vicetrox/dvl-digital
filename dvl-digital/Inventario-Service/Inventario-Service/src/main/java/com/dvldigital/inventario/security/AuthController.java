package com.dvldigital.inventario.security;

import com.dvldigital.inventario.model.entity.Usuario;
import com.dvldigital.inventario.service.UsuarioService;
import com.dvldigital.inventario.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String contraseña) {
        Optional<Usuario> usuario = usuarioService.obtenerPorEmail(email);
        
        if (usuario.isPresent() && usuario.get().getContraseña().equals(contraseña)) {
            String token = jwtUtils.generarToken(email);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("token", token);
            respuesta.put("email", email);
            respuesta.put("rol", usuario.get().getRol());
            return ResponseEntity.ok(respuesta);
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("credenciales invalidas");
    }
    
    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setActivo("1");
        usuario.setRol("c");
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(usuario));
    }
    
    @GetMapping("/validar")
    public ResponseEntity<Boolean> validarToken(@RequestHeader String Authorization) {
        if (Authorization != null && Authorization.startsWith("Bearer ")) {
            String token = Authorization.substring(7);
            return ResponseEntity.ok(jwtUtils.validarToken(token));
        }
        return ResponseEntity.ok(false);
    }
}