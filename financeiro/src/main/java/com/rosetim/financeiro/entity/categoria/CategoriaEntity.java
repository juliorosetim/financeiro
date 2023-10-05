package com.rosetim.financeiro.entity.categoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "categoria")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_seq")
    @SequenceGenerator(name = "categoria_seq", sequenceName = "categoria_seq", allocationSize = 1)
    @Column(name = "cdcategoria")
    Long cdCategoria;

    @Column(name = "decategoria")
    @NotNull
    String deCategoria;

    @Column(name = "metamensal")
    BigDecimal metaMensal;
}
