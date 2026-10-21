package com.taller.sistema_prestamos.dto;

import java.time.LocalDate;

public class PrestamoRequest {
    private String responsable;
    private LocalDate fechaPrestamo;
    private int cantidad;
    private Long herramientaId;

    // Getters y Setters
    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }

    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Long getHerramientaId() { return herramientaId; }
    public void setHerramientaId(Long herramientaId) { this.herramientaId = herramientaId; }
}