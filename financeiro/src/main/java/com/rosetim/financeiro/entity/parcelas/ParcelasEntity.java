package com.rosetim.financeiro.entity.parcelas;

import com.rosetim.financeiro.entity.gastos.GastosEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "parcelas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParcelasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parcelas_seq")
    @SequenceGenerator(name = "parcelas_seq", sequenceName = "parcelas_seq", allocationSize = 1)
    @Column(name = "cdparcela")
    Long cdParcela;

    @Column(name = "cdgasto")
    Long cdGasto;

    @Column(name = "qtdeparcela")
    Integer qtdeParcela;

    @Column(name = "vlrparcela")
    BigDecimal vlrParcela;

    @Column(name = "vlrtotal")
    BigDecimal vlrTotal;

    @Column(name = "dtvencimento")
    LocalDate dtVencimento;

    String conferido;

    @Column(name = "nuparcela")
    Integer nuParcela;

    @ManyToOne()
    @JoinColumn(name = "cdgasto", referencedColumnName = "cdgasto", insertable = false, updatable = false)
    GastosEntity gasto;
}
