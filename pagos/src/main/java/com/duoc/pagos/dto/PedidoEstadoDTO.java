package com.duoc.pagos.dto;

public class PedidoEstadoDTO {
    private String estado;

    public PedidoEstadoDTO() {
    }

    public PedidoEstadoDTO(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}