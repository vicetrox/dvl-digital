package com.duoc.pagos.service;

import com.duoc.pagos.model.Pago;
import com.duoc.pagos.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Pago procesarPago(Pago pago) {
        try {
            // 1. LLAMAMOS A PEDIDOS PARA SABER EL TOTAL REAL DE LA ORDEN
            String urlObtenerPedido = "http://localhost:8081/api/pedidos/" + pago.getPedidoId();
            
            // Recibimos la respuesta como un "Mapa" (como un JSON crudo)
            Map pedido = restTemplate.getForObject(urlObtenerPedido, Map.class);
            
            if (pedido == null || !pedido.containsKey("total")) {
                pago.setEstado("RECHAZADO_PEDIDO_NO_EXISTE");
                return pagoRepository.save(pago);
            }

            // Sacamos el total que nos respondió el microservicio de Pedidos
            Double totalReal = Double.valueOf(pedido.get("total").toString());

            // 2. LA GRAN VALIDACIÓN: ¿La plata alcanza?
            if (pago.getMonto() < totalReal) {
                System.out.println("Pago rechazado. Se requieren $" + totalReal + " pero mandaron $" + pago.getMonto());
                pago.setEstado("RECHAZADO_MONTO_INSUFICIENTE");
                return pagoRepository.save(pago); // Guardamos pero NO le avisamos a Pedidos que se pagó
            }

            // 3. SI LA PLATA ALCANZA, APROBAMOS Y AVISAMOS
            pago.setEstado("APROBADO");
            Pago pagoGuardado = pagoRepository.save(pago);

            // Avisamos al microservicio de Pedidos (8081)
            String urlPedidoPut = "http://localhost:8081/api/pedidos/" + pago.getPedidoId() + "/estado";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            Map<String, String> body = new HashMap<>();
            body.put("estado", "PAGADO");
            
            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            restTemplate.put(urlPedidoPut, request);
            
            System.out.println("¡Pago exitoso y aviso enviado a Pedidos!");
            return pagoGuardado;

        } catch (Exception e) {
            System.out.println("Error al procesar el pago o conectar con Pedidos: " + e.getMessage());
            pago.setEstado("ERROR_DE_COMUNICACION");
            return pagoRepository.save(pago);
        }
    }

    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }
}