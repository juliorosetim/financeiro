package com.rosetim.financeiro.entity.gastos;

import com.rosetim.financeiro.entity.cartao.CartaoEntity;
import com.rosetim.financeiro.entity.categoria.CategoriaEntity;
import com.rosetim.financeiro.entity.formaPagto.FormaPagtoEntity;
import com.rosetim.financeiro.entity.grupo.GrupoEntity;
import com.rosetim.financeiro.entity.usuario.UsuarioEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gastos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GastosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gastos_seq")
    @SequenceGenerator(name = "gastos_seq", sequenceName = "gastos_seq", allocationSize = 1)
    @Column(name = "cdgasto")
    Long cdGasto;

    @Column(name = "defatura")
    String deFatura;

    @Column(name = "dedescricao")
    String deDescricao;

    @OneToOne()
    @JoinColumn(name = "cdgrupo")
    GrupoEntity grupo;

    @OneToOne()
    @JoinColumn(name = "cdcategoria")
    CategoriaEntity categoria;

    @OneToOne()
    @JoinColumn(name = "cdformaPagto")
    FormaPagtoEntity formaPagto;

    @ManyToOne(optional = true)
    @JoinColumn(name = "cdcartao")
    CartaoEntity cartao;

    @NotNull
    @Column(name = "qtdeparcela")
    Integer qtdeParcela;

    @Column(name = "vlrtotal")
    BigDecimal vlrTotal;

    @OneToOne()
    @JoinColumn(name = "cdusuario")
    UsuarioEntity usuario;

    @Column(name = "dtlancamento")
    LocalDate dtLancamento;

    @Column(name = "pago")
    String pago;

    @Column(name = "tplancamento")
    String tpLancamento;
}
