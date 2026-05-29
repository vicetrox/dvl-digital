package com.dvldigital.inventario.controller;

import com.dvldigital.inventario.model.entity.MovimientoStock;
import com.dvldigital.inventario.model.entity.Producto;
import com.dvldigital.inventario.service.MovimientoStockService;
import com.dvldigital.inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoStockController {
    
    @Autowired
    private MovimientoStockService movimientoService;
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping
    public ResponseEntity<List<MovimientoStock>> obtenerTodos() {
        return ResponseEntity.ok(movimientoService.obtenerTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoStock> obtenerPorId(@PathVariable String id) {
        Optional<MovimientoStock> movimiento = movimientoService.obtenerPorId(id);
        return movimiento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/entrada")
    public ResponseEntity<MovimientoStock> registrarEntrada(@RequestBody MovimientoStock movimiento) {
        movimiento.setTipoMovimiento("entrada");
        movimiento.setFechaMovimiento(LocalDate.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoService.crear(movimiento));
    }
    
    @PostMapping("/salida")
    public ResponseEntity<MovimientoStock> registrarSalida(@RequestBody MovimientoStock movimiento) {
        movimiento.setTipoMovimiento("salida");
        movimiento.setFechaMovimiento(LocalDate.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoService.crear(movimiento));
    }
    
    @PostMapping("/devolucion")
    public ResponseEntity<MovimientoStock> registrarDevolucion(@RequestBody MovimientoStock movimiento) {
        movimiento.setTipoMovimiento("devolucion");
        movimiento.setFechaMovimiento(LocalDate.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoService.crear(movimiento));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        movimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}