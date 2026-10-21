package com.taller.sistema_prestamos.Controller;

import com.taller.sistema_prestamos.Entity.Prestamo;
import com.taller.sistema_prestamos.dto.PrestamoRequest;
import com.taller.sistema_prestamos.Service.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<?> registrarPrestamo(@RequestBody PrestamoRequest request) {
        try {
            Prestamo prestamo = prestamoService.registrarPrestamo(request);
            return ResponseEntity.ok(prestamo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> consultarTodosLosPrestamos() {
        return ResponseEntity.ok(prestamoService.listarTodosLosPrestamos());
    }

    @GetMapping("/herramienta/{id}")
    public ResponseEntity<List<Prestamo>> consultarPrestamosPorHerramienta(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.listarPrestamosPorHerramienta(id));
    }
}