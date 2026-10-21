package com.taller.sistema_prestamos.Service;

import com.taller.sistema_prestamos.Entity.Herramienta;
import com.taller.sistema_prestamos.Entity.Prestamo;
import com.taller.sistema_prestamos.Repository.HerramientaRepository;
import com.taller.sistema_prestamos.Repository.PrestamoRepository;
import com.taller.sistema_prestamos.dto.PrestamoRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final HerramientaRepository herramientaRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, HerramientaRepository herramientaRepository) {
        this.prestamoRepository = prestamoRepository;
        this.herramientaRepository = herramientaRepository;
    }

    public List<Prestamo> listarTodosLosPrestamos() {
        return prestamoRepository.findAll();
    }

    public List<Prestamo> listarPrestamosPorHerramienta(Long herramientaId) {
        return prestamoRepository.findByHerramientaId(herramientaId);
    }

    @Transactional
    public Prestamo registrarPrestamo(PrestamoRequest request) {
        // Validar cantidad no cero o negativa
        if (request.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad a prestar debe ser mayor a cero.");
        }

        // Comprobar que la herramienta exista
        Herramienta herramienta = herramientaRepository.findById(request.getHerramientaId())
                .orElseThrow(() -> new RuntimeException("La herramienta con ID " + request.getHerramientaId() + " no existe."));

        // Comprobar stock suficiente
        if (request.getCantidad() > herramienta.getCantidad()) {
            throw new IllegalArgumentException("Préstamo rechazado: la cantidad solicitada supera las unidades disponibles.");
        }

        // Descontar las unidades del inventario
        herramienta.setCantidad(herramienta.getCantidad() - request.getCantidad());
        herramientaRepository.save(herramienta);

        // Crear y guardar el préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setResponsable(request.getResponsable());
        prestamo.setFechaPrestamo(request.getFechaPrestamo());
        prestamo.setCantidad(request.getCantidad());
        prestamo.setHerramienta(herramienta);

        return prestamoRepository.save(prestamo);
    }
}