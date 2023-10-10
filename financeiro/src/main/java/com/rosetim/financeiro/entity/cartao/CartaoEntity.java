package com.rosetim.financeiro.entity.cartao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull(message = "Cartão deve ser preenchido")
    @NotBlank(message = "Cartão deve ser preenchido")
    String deCartao;

    @Column(name = "diavirada")
    @NotNull(message = "Dia do fechamento deve ser preenchida")
    Integer diaVirada;

    @Column(name = "diavencimento")
    @NotNull(message = "Dia do vencimento deve ser preenchido")
//    @NotBlank(message = "Dia do vencimento deve ser preenchido")
    Integer diaVencimento;
}
