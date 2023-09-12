package com.rosetim.financeiro.repository.grupo;

import com.rosetim.financeiro.entity.grupo.GrupoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoEntity, Long> {
}
