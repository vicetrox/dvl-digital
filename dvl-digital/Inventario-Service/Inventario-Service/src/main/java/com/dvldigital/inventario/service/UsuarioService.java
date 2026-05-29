package com.dvldigital.inventario.service;

import com.dvldigital.inventario.model.entity.Usuario;
import com.dvldigital.inventario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> obtenerPorId(String id) {
        return usuarioRepository.findById(id);
    }
    
    public Optional<Usuario> obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    public Usuario crear(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public Usuario actualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public void eliminar(String id) {
        usuarioRepository.deleteById(id);
    }
}