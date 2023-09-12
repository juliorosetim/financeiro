package com.rosetim.financeiro.repository.gastos;

import com.rosetim.financeiro.entity.gastos.GastosEntity;
import com.rosetim.financeiro.entity.grupo.GrupoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastosRepository extends JpaRepository<GastosEntity, Long> {
}
