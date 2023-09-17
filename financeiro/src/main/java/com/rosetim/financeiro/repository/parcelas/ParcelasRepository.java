package com.rosetim.financeiro.repository.parcelas;

import com.rosetim.financeiro.entity.parcelas.ParcelasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelasRepository extends JpaRepository<ParcelasEntity, Long> {
}
