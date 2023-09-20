package com.rosetim.financeiro.repository.parcelas;

import com.rosetim.financeiro.dto.ParcelasGastosDto;
import com.rosetim.financeiro.entity.parcelas.ParcelasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ParcelasRepository extends JpaRepository<ParcelasEntity, Long> {

    @Query("Select c.deCartao, g.deFatura, g.deDescricao, p from ParcelasEntity p " +
            " join GastosEntity g on p.cdGasto = g.cdGasto " +
            " join CartaoEntity c on c.cdCartao = g.cartao.cdCartao " +
            "where p.dtVencimento <= :dtProximoVencimento and p.dtVencimento >= :dtHoje order by p.nuParcela asc" )
    List<Object[]> findParcelasByData(@Param("dtProximoVencimento") LocalDate dtProximoVencimento,
                                               @Param("dtHoje") LocalDate dtHoje);
}
