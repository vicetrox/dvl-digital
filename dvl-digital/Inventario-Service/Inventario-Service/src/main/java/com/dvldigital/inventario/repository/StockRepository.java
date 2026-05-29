package com.dvldigital.inventario.repository;

import com.dvldigital.inventario.model.entity.Stock;
import com.dvldigital.inventario.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
    Optional<Stock> findByProducto(Producto producto);
}