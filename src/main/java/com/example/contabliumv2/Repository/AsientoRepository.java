package com.example.contabliumv2.Repository;

import com.example.contabliumv2.Model.cuentas_asientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AsientoRepository extends JpaRepository<cuentas_asientos,Long> {

}
