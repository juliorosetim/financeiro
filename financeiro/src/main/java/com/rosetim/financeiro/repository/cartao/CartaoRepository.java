package com.rosetim.financeiro.repository.cartao;

import com.rosetim.financeiro.entity.cartao.CartaoEntity;
import com.rosetim.financeiro.entity.formaPagto.FormaPagtoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {

    CartaoEntity findByCdCartao(Long cdCartao);
}
