package com.dvldigital.inventario.service;

import com.dvldigital.inventario.model.entity.DetalleOrden;
import com.dvldigital.inventario.model.entity.Orden;
import com.dvldigital.inventario.repository.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenService {
    
    @Autowired
    private DetalleOrdenRepository detalleRepository;
    
    public List<DetalleOrden> obtenerTodos() {
        return detalleRepository.findAll();
    }
    
    public Optional<DetalleOrden> obtenerPorId(String id) {
        return detalleRepository.findById(id);
    }
    
    public List<DetalleOrden> obtenerPorOrden(Orden orden) {
        return detalleRepository.findByOrden(orden);
    }
    
    public DetalleOrden crear(DetalleOrden detalle) {
        return detalleRepository.save(detalle);
    }
    
    public DetalleOrden actualizar(DetalleOrden detalle) {
        return detalleRepository.save(detalle);
    }
    
    public void eliminar(String id) {
        detalleRepository.deleteById(id);
    }
}