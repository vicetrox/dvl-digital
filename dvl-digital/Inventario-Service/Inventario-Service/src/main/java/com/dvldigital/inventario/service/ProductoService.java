package com.dvldigital.inventario.service;

import com.dvldigital.inventario.model.entity.Producto;
import com.dvldigital.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }
    
    public Optional<Producto> obtenerPorId(String id) {
        return productoRepository.findById(id);
    }
    
    public Optional<Producto> obtenerPorSku(String sku) {
        return productoRepository.findBySku(sku);
    }
    
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }
    
    public Producto actualizar(Producto producto) {
        return productoRepository.save(producto);
    }
    
    public void eliminar(String id) {
        productoRepository.deleteById(id);
    }
}