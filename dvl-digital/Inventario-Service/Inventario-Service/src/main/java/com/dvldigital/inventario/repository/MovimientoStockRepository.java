package com.dvldigital.inventario.repository;

import com.dvldigital.inventario.model.entity.MovimientoStock;
import com.dvldigital.inventario.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimientoStockRepository extends JpaRepository<MovimientoStock, String> {
    List<MovimientoStock> findByProducto(Producto producto);
}