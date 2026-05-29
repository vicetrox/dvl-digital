package com.duoc.pagos.controller;

import com.duoc.pagos.model.Pago;
import com.duoc.pagos.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService; // Llamamos al Service

    @PostMapping
    public ResponseEntity<Pago> registrarPago(@RequestBody Pago pago) {
        Pago pagoProcesado = pagoService.procesarPago(pago);
        return ResponseEntity.ok(pagoProcesado);
    }

    @GetMapping
    public List<Pago> listarPagos() {
        return pagoService.obtenerTodos();
    }
}