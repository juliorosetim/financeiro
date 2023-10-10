package com.rosetim.financeiro.entity.formaPagto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull(message = "Forma de pagamento deve ser preenchido")
    @NotBlank(message = "Forma de pagamento deve ser preenchido")
    String deFormaPagto;

    @Column(name = "tipo")
    String tipo;
}
