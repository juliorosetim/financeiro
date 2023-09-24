package com.rosetim.financeiro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParcelasGastosDto {

    String deFatura;
    String deDescricao;
    Long cdParcela;
    Long cdGasto;
    Integer qtdeParcela;
    BigDecimal vlrParcela;
    BigDecimal vlrtotal;
    LocalDate dtVencimento;
    String conferido;
    Integer nuParcela;
    String deCartao;
    String deCategoria;
}
