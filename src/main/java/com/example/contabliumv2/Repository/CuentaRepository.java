package com.example.contabliumv2.Repository;

import com.example.contabliumv2.Model.Cuenta;
import com.example.contabliumv2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Integer> {

    Cuenta findByNombre (String nombre);

    Cuenta findByIdCuenta(Integer id);
}
