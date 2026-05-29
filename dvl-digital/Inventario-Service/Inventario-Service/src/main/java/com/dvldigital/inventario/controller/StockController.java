package com.dvldigital.inventario.controller;

import com.dvldigital.inventario.model.entity.Stock;
import com.dvldigital.inventario.model.entity.Producto;
import com.dvldigital.inventario.service.StockService;
import com.dvldigital.inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    
    @Autowired
    private StockService stockService;
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping
    public ResponseEntity<List<Stock>> obtenerTodos() {
        return ResponseEntity.ok(stockService.obtenerTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Stock> obtenerPorId(@PathVariable String id) {
        Optional<Stock> stock = stockService.obtenerPorId(id);
        return stock.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Stock> crear(@RequestBody Stock stock) {
        stock.setUltimaActualizacion(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.crear(stock));
    }
    
    @PutMapping("/{id}/ajustar")
    public ResponseEntity<Stock> ajustarCantidad(@PathVariable String id, @RequestParam Integer cantidad) {
        Optional<Stock> stock = stockService.obtenerPorId(id);
        if (stock.isPresent()) {
            Stock s = stock.get();
            s.setCantidadDisponible(cantidad);
            s.setUltimaActualizacion(LocalDateTime.now());
            return ResponseEntity.ok(stockService.actualizar(s));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        stockService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}