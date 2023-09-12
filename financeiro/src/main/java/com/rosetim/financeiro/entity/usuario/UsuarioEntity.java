package com.rosetim.financeiro.entity.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq",sequenceName = "ususario_seq", allocationSize = 1)
    @Column(name = "cdusuario")
    Long cdUsuario;

    @Column(name = "deusuario")
    String deUsuario;

    String senha;

    @Column(name = "flativo")
    String flAtivo;
}
