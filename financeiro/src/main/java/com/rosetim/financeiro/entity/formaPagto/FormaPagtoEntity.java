package com.rosetim.financeiro.entity.formaPagto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "formapgto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormaPagtoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formapagto_seq")
    @SequenceGenerator(name = "formapagto_seq", sequenceName = "formapagto_seq", allocationSize = 1)
    @Column(name = "cdformapagto")
    Long cdFormaPagto;

    @Column(name = "deformapagto")
    @NotNull
    String deFormaPagto;
}
