package com.taller.sistema_prestamos.Controller;

import com.taller.sistema_prestamos.Entity.Herramienta;
import com.taller.sistema_prestamos.service.HerramientaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
@CrossOrigin(origins = "*")
public class HerramientaController {

    private final HerramientaService herramientaService;

    public HerramientaController(HerramientaService herramientaService) {
        this.herramientaService = herramientaService;
    }

    @PostMapping
    public ResponseEntity<Herramienta> registrarHerramienta(@RequestBody Herramienta herramienta) {
        Herramienta nueva = herramientaService.guardarHerramienta(herramienta);
        return ResponseEntity.ok(nueva);
    }

    @GetMapping
    public ResponseEntity<List<Herramienta>> consultarHerramientas() {
        return ResponseEntity.ok(herramientaService.listarHerramientas());
    }
}