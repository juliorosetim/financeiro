package com.rosetim.financeiro.entity.grupo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull(message = "Grupo deve ser preenchido")
    @NotBlank(message = "Grupo deve ser preenchido")
    String deGrupo;
}
