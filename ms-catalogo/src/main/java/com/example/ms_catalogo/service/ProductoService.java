package com.example.ms_catalogo.service;

import com.example.ms_catalogo.model.Producto;
import com.example.ms_catalogo.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> actualizarProducto(Long id, Producto detalles) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(detalles.getNombre());
            producto.setDescripcion(detalles.getDescripcion());
            producto.setPrecio(detalles.getPrecio());
            producto.setStock(detalles.getStock());
            return productoRepository.save(producto);
        });
    }

    public boolean eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}