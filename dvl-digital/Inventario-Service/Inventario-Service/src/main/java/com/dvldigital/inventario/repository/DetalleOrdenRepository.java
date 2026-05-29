package com.dvldigital.inventario.repository;

import com.dvldigital.inventario.model.entity.DetalleOrden;
import com.dvldigital.inventario.model.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, String> {
    List<DetalleOrden> findByOrden(Orden orden);
}