package com.dvldigital.inventario.service;

import com.dvldigital.inventario.model.entity.Stock;
import com.dvldigital.inventario.model.entity.Producto;
import com.dvldigital.inventario.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    
    @Autowired
    private StockRepository stockRepository;
    
    public List<Stock> obtenerTodos() {
        return stockRepository.findAll();
    }
    
    public Optional<Stock> obtenerPorId(String id) {
        return stockRepository.findById(id);
    }
    
    public Optional<Stock> obtenerPorProducto(Producto producto) {
        return stockRepository.findByProducto(producto);
    }
    
    public Stock crear(Stock stock) {
        return stockRepository.save(stock);
    }
    
    public Stock actualizar(Stock stock) {
        return stockRepository.save(stock);
    }
    
    public void eliminar(String id) {
        stockRepository.deleteById(id);
    }
}