package com.taller.sistema_prestamos.service;

import com.taller.sistema_prestamos.Entity.Herramienta;
import com.taller.sistema_prestamos.Repository.HerramientaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HerramientaService {

    private final HerramientaRepository herramientaRepository;

    public HerramientaService(HerramientaRepository herramientaRepository) {
        this.herramientaRepository = herramientaRepository;
    }

    public List<Herramienta> listarHerramientas() {
        return herramientaRepository.findAll();
    }

    public Herramienta guardarHerramienta(Herramienta herramienta) {
        if (herramienta.getCantidad() < 0) {
            throw new IllegalArgumentException("No se permiten cantidades negativas para las herramientas.");
        }
        return herramientaRepository.save(herramienta);
    }

    public Optional<Herramienta> obtenerPorId(Long id) {
        return herramientaRepository.findById(id);
    }
}