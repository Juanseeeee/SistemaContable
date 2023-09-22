package com.example.contabliumv2.Repository;

import com.example.contabliumv2.Model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
    @Override
    default <S extends Cuenta> S save(S entity) {
        return null;
    }

    @Override
    default Optional<Cuenta> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    default void deleteById(Long aLong) {

    }
}
