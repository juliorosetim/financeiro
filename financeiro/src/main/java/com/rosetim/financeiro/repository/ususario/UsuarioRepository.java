package com.rosetim.financeiro.repository.ususario;

import com.rosetim.financeiro.entity.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
