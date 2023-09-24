package com.rosetim.financeiro.repository.categoria;

import com.rosetim.financeiro.entity.categoria.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}
