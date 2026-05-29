package com.dvldigital.inventario.service;

import com.dvldigital.inventario.model.entity.Orden;
import com.dvldigital.inventario.model.entity.Usuario;
import com.dvldigital.inventario.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

@Service
public class OrdenService {
    
    @Autowired
    private OrdenRepository ordenRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(OrdenService.class);

    @Autowired
    private RestTemplate restTemplate;

    public List<Orden> obtenerTodos() {
        return ordenRepository.findAll();
    }
    
    public Optional<Orden> obtenerPorId(String id) {
        return ordenRepository.findById(id);
    }
    
    public List<Orden> obtenerPorCliente(Usuario usuario) {
        return ordenRepository.findByUsuarioCliente(usuario);
    }
    
    public Orden crear(Orden orden) {
        logger.info("OrdenService: Iniciando creacion de orden para usuario: {}", 
                    orden.getUsuarioCliente().getEmail());
        
        // PASO 1: Guardar orden en BD Inventario
        orden.setFechaCreacion(LocalDate.now());
        orden.setEstado("pendiente");
        
        Orden ordenGuardada = ordenRepository.save(orden);
        logger.info("OrdenService: Orden guardada con ID: {}", ordenGuardada.getId());
        
        // PASO 2: Notificar a Notificaciones-Service
        try {
            logger.info("OrdenService: Llamando a Notificaciones en http://localhost:8081");
            
            Map<String, Object> notificacion = new HashMap<>();
            notificacion.put("destinatario", orden.getUsuarioCliente().getEmail());
            notificacion.put("asunto", "Tu orden ha sido creada");
            notificacion.put("cuerpo", "Tu orden #" + ordenGuardada.getId() + " ha sido registrada");
            notificacion.put("tipoEvento", "orden_creada");
            notificacion.put("estado", "pendiente");
            
            restTemplate.postForObject(
                "http://localhost:8081/api/notificaciones",
                notificacion,
                String.class
            );
            
            logger.info("OrdenService: Notificacion enviada exitosamente a Notificaciones");
            
        } catch (Exception e) {
            logger.error("OrdenService: Error al llamar a Notificaciones: {}", e.getMessage());
        }
        
        logger.info("OrdenService: Proceso completado. Orden devuelta al cliente");
        return ordenGuardada;
    }
    
    public Orden actualizar(Orden orden) {
        return ordenRepository.save(orden);
    }
    
    public void eliminar(String id) {
        ordenRepository.deleteById(id);
    }
}