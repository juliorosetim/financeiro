package com.rosetim.financeiro.entity.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Usuário deve ser preenchido")
    @NotBlank(message = "Usuário deve ser preenchido")
    String deUsuario;

    @NotNull(message = "Senha deve ser preenchida")
    @NotBlank(message = "Senha deve ser preenchida")
    String senha;

    @Column(name = "flativo")
    String flAtivo;
}
