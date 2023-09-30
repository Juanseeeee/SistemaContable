package com.example.contabliumv2.Repository;

import com.example.contabliumv2.Model.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle,Long> {

}
