package com.example.contabliumv2.Repository;

import com.example.contabliumv2.Model.Asiento;
import com.example.contabliumv2.Model.Cuenta;
import com.example.contabliumv2.Model.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle,Long> {

    List<Detalle> findAllByAsiento(Asiento asiento);
    List<Detalle> findAllByCuenta_IdCuenta(Integer idCuenta);
    List<Detalle> findAllByCuenta(Cuenta cuenta);

}
