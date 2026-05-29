package com.dvldigital.inventario.service;

import com.dvldigital.inventario.model.entity.MovimientoStock;
import com.dvldigital.inventario.model.entity.Producto;
import com.dvldigital.inventario.repository.MovimientoStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoStockService {
    
    @Autowired
    private MovimientoStockRepository movimientoRepository;
    
    public List<MovimientoStock> obtenerTodos() {
        return movimientoRepository.findAll();
    }
    
    public Optional<MovimientoStock> obtenerPorId(String id) {
        return movimientoRepository.findById(id);
    }
    
    public List<MovimientoStock> obtenerPorProducto(Producto producto) {
        return movimientoRepository.findByProducto(producto);
    }
    
    public MovimientoStock crear(MovimientoStock movimiento) {
        return movimientoRepository.save(movimiento);
    }
    
    public MovimientoStock actualizar(MovimientoStock movimiento) {
        return movimientoRepository.save(movimiento);
    }
    
    public void eliminar(String id) {
        movimientoRepository.deleteById(id);
    }
}