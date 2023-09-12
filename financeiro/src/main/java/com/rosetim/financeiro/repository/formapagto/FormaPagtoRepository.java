package com.rosetim.financeiro.repository.formapagto;

import com.rosetim.financeiro.entity.formaPagto.FormaPagtoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagtoRepository extends JpaRepository<FormaPagtoEntity, Long> {
}
