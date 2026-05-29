package com.dvldigital.inventario.repository;

import com.dvldigital.inventario.model.entity.Orden;
import com.dvldigital.inventario.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, String> {
    List<Orden> findByUsuarioCliente(Usuario usuario);
}