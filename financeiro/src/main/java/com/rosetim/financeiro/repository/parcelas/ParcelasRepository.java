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

    @Query("Select c.deCartao, g.deFatura, g.deDescricao, ca.deCategoria, p from ParcelasEntity p " +
            " join GastosEntity g on p.cdGasto = g.cdGasto " +
            " left join CartaoEntity c on c.cdCartao = g.cartao.cdCartao " +
            " join CategoriaEntity ca on ca.cdCategoria = g.categoria.cdCategoria " +
            "where p.dtVencimento >= :dtInicio and p.dtVencimento <= :dtFim order by p.nuParcela asc" )
    List<Object[]> findParcelasByData(@Param("dtInicio") LocalDate dtInicio,
                                               @Param("dtFim") LocalDate dtFim);

    List<ParcelasEntity> findByCdGasto(Long cdGasto);

    @Query("Select c.deCartao, g.deFatura, g.deDescricao, ca.deCategoria, p from ParcelasEntity p " +
            " join GastosEntity g on p.cdGasto = g.cdGasto " +
            " left join CartaoEntity c on c.cdCartao = g.cartao.cdCartao " +
            " join CategoriaEntity ca on ca.cdCategoria = g.categoria.cdCategoria " +
            "where p.cdGasto = :cdGasto" )
    List<Object[]> findParcelasByCdGasto(@Param("cdGasto") Long cdGasto);

    @Query("Select  c.deCartao, sum(p.vlrParcela) from ParcelasEntity p " +
            " join GastosEntity g on p.cdGasto = g.cdGasto " +
            " join CartaoEntity c on c.cdCartao = g.cartao.cdCartao " +
            "where p.dtVencimento >= :dtInicio and p.dtVencimento <= :dtFim group by c.deCartao" )
    List<Object[]> findParcelasGroupByCartao(@Param("dtInicio") LocalDate dtInicio,
                                                @Param("dtFim") LocalDate dtFim);

    @Query("Select  f.deFormaPagto, sum(p.vlrParcela) from ParcelasEntity p " +
            " join GastosEntity g on p.cdGasto = g.cdGasto " +
            " join FormaPagtoEntity f on f.cdFormaPagto = g.formaPagto.cdFormaPagto " +
            "where p.dtVencimento >= :dtInicio and p.dtVencimento <= :dtFim group by f.deFormaPagto" )
    List<Object[]> findParcelasGroupByFormaPgto(@Param("dtInicio") LocalDate dtInicio,
                                             @Param("dtFim") LocalDate dtFim);

    @Query("Select  ca.deCategoria, sum(p.vlrParcela) from ParcelasEntity p " +
            " join GastosEntity g on p.cdGasto = g.cdGasto " +
            " join CategoriaEntity ca on ca.cdCategoria = g.categoria.cdCategoria " +
            "where p.dtVencimento >= :dtInicio and p.dtVencimento <= :dtFim group by ca.deCategoria" )
    List<Object[]> findParcelasGroupByCategoria(@Param("dtInicio") LocalDate dtInicio,
                                                @Param("dtFim") LocalDate dtFim);

    @Query("Select  gg.deGrupo, sum(p.vlrParcela) from ParcelasEntity p " +
            " join GastosEntity g on p.cdGasto = g.cdGasto " +
            " join GrupoEntity gg on gg.cdGrupo = g.grupo.cdGrupo " +
            "where p.dtVencimento >= :dtInicio and p.dtVencimento <= :dtFim group by gg.deGrupo" )
    List<Object[]> findParcelasGroupByGrupo(@Param("dtInicio") LocalDate dtInicio,
                                                @Param("dtFim") LocalDate dtFim);

    @Query("Select  sum(p.vlrParcela) from ParcelasEntity p " +
            " join GastosEntity g on p.cdGasto = g.cdGasto " +
            "where p.dtVencimento >= :dtInicio and p.dtVencimento <= :dtFim" )
    List<Object[]> findParcelasPorDatas(@Param("dtInicio") LocalDate dtInicio,
                                            @Param("dtFim") LocalDate dtFim);
}
