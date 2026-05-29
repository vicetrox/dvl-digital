package com.dvldigital.inventario.controller;

import com.dvldigital.inventario.model.entity.Orden;
import com.dvldigital.inventario.model.entity.Usuario;
import com.dvldigital.inventario.service.OrdenService;
import com.dvldigital.inventario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {
    
    @Autowired
    private OrdenService ordenService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<List<Orden>> obtenerTodos() {
        return ResponseEntity.ok(ordenService.obtenerTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerPorId(@PathVariable String id) {
        Optional<Orden> orden = ordenService.obtenerPorId(id);
        return orden.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Orden> crear(@RequestBody Orden orden) {
        orden.setFechaCreacion(LocalDate.now());
        orden.setEstado("pendiente");
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.crear(orden));
    }
    
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Orden> confirmar(@PathVariable String id) {
        Optional<Orden> orden = ordenService.obtenerPorId(id);
        if (orden.isPresent()) {
            Orden o = orden.get();
            o.setEstado("confirmada");
            return ResponseEntity.ok(ordenService.actualizar(o));
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/despachar")
    public ResponseEntity<Orden> despachar(@PathVariable String id) {
        Optional<Orden> orden = ordenService.obtenerPorId(id);
        if (orden.isPresent()) {
            Orden o = orden.get();
            o.setEstado("despachada");
            return ResponseEntity.ok(ordenService.actualizar(o));
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/entregar")
    public ResponseEntity<Orden> entregar(@PathVariable String id) {
        Optional<Orden> orden = ordenService.obtenerPorId(id);
        if (orden.isPresent()) {
            Orden o = orden.get();
            o.setEstado("entregada");
            o.setFechaEntrega(LocalDate.now());
            return ResponseEntity.ok(ordenService.actualizar(o));
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Orden> cancelar(@PathVariable String id) {
        Optional<Orden> orden = ordenService.obtenerPorId(id);
        if (orden.isPresent()) {
            Orden o = orden.get();
            o.setEstado("cancelada");
            return ResponseEntity.ok(ordenService.actualizar(o));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        ordenService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}