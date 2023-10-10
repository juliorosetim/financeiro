package com.rosetim.financeiro.entity.gastos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rosetim.financeiro.entity.cartao.CartaoEntity;
import com.rosetim.financeiro.entity.categoria.CategoriaEntity;
import com.rosetim.financeiro.entity.formaPagto.FormaPagtoEntity;
import com.rosetim.financeiro.entity.grupo.GrupoEntity;
import com.rosetim.financeiro.entity.usuario.UsuarioEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull(message = "Descrição do lançamento deve ser preenchida")
    @NotBlank(message = "Descrição do lançamento deve ser preenchida")
    String deDescricao;

    @OneToOne()
    @JoinColumn(name = "cdgrupo")
    @NotNull(message = "Grupo deve ser preenchido")
    GrupoEntity grupo;

    @OneToOne()
    @JoinColumn(name = "cdcategoria")
    @NotNull(message = "Categoria deve ser preenchida")
    CategoriaEntity categoria;

    @OneToOne()
    @JoinColumn(name = "cdformaPagto")
    @NotNull(message = "Forma de pagamento deve ser preenchida")
    FormaPagtoEntity formaPagto;

    @ManyToOne(optional = true)
    @JoinColumn(name = "cdcartao")
    CartaoEntity cartao;

    @NotNull
    @Column(name = "qtdeparcela")
    Integer qtdeParcela;

    @Column(name = "vlrtotal")
    @NotNull(message = "Valor total deve ser preenchido")
//    @Digits(integer = 12, fraction = 3, message = "Valor da taxa inválida")
    BigDecimal vlrTotal;

    @OneToOne()
    @JoinColumn(name = "cdusuario")
    UsuarioEntity usuario;

    @Column(name = "dtlancamento")
    @NotNull(message = "Data de lançamento deve ser preenchido")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate dtLancamento;

    @Column(name = "pago")
    String pago;

    @Column(name = "tplancamento")
    @NotNull(message = "Tipo de lançamento deve ser preenchido")
    @NotBlank(message = "Tipo de lançamento deve ser preenchido")
    String tpLancamento;
}
