package com.taller.sistema_prestamos.Repository;

import com.taller.sistema_prestamos.Entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByHerramientaId(Long herramientaId);
}