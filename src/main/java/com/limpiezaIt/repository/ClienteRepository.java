package com.limpiezaIt.repository;

import com.limpiezaIt.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Metodo para desactivar producto
    @Modifying
    @Query("UPDATE Cliente c SET c.activo = false WHERE p.id = :id")
    void desactivarCliente(@Param("id") Long id);
}
