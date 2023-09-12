package com.rosetim.financeiro.entity.cartao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "cartao")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartao_seq")
    @SequenceGenerator(name = "cartao_seq", sequenceName = "cartao_seq", allocationSize = 1)
    @Column(name = "cdcartao")
    Long cdCartao;

    @Column(name = "decartao")
    @NotNull
    String deCartao;

    @Column(name = "diavirada")
    Integer diaVirada;

    @Column(name = "diavencimento")
    Integer diaVencimento;
}
