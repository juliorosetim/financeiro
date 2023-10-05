package com.rosetim.financeiro.repository.gastos;

import com.rosetim.financeiro.entity.cartao.CartaoEntity;
import com.rosetim.financeiro.entity.categoria.CategoriaEntity;
import com.rosetim.financeiro.entity.formaPagto.FormaPagtoEntity;
import com.rosetim.financeiro.entity.gastos.GastosEntity;
import com.rosetim.financeiro.entity.grupo.GrupoEntity;
import com.rosetim.financeiro.entity.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GastosRepository extends JpaRepository<GastosEntity, Long> {

    List<GastosEntity> findByCartao(CartaoEntity cartao);

    List<GastosEntity> findByCategoria(Optional<CategoriaEntity> categoria);

    List<GastosEntity> findByFormaPagto(Optional<FormaPagtoEntity> formaPagto);

    List<GastosEntity> findByGrupo(Optional<GrupoEntity> grupo);

    List<GastosEntity> findByUsuario(Optional<UsuarioEntity> usuario);
}
