package com.rosetim.financeiro.entity.grupo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "grupo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GrupoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grupo_seq")
    @SequenceGenerator(name = "grupo_seq", sequenceName = "grupo_seq", allocationSize = 1)
    @Column(name = "cdgrupo")
    Long cdGrupo;

    @Column(name = "degrupo")
    @NotNull
    String deGrupo;
}
