package com.taller.sistema_prestamos.Repository;

import com.taller.sistema_prestamos.Entity.Herramienta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {
}