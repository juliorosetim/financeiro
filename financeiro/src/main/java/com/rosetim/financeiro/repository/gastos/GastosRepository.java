package com.rosetim.financeiro.repository.gastos;

import com.rosetim.financeiro.entity.gastos.GastosEntity;
import com.rosetim.financeiro.entity.grupo.GrupoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GastosRepository extends JpaRepository<GastosEntity, Long> {

//    @Query("Select c.deCartao, ca.deCategoria, g from GastosEntity g " +
//            " join CartaoEntity c on c.cdCartao = g.cartao.cdCartao " +
//            " join CategoriaEntity ca on ca.cdCategoria = g.categoria.cdCategoria ")
//    List<Object[]> findGastos(@Param("dtProximoVencimento") LocalDate dtProximoVencimento,
//                                      @Param("dtHoje") LocalDate dtHoje);

}
