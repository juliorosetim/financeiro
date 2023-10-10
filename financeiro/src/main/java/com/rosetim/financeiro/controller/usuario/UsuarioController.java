package com.rosetim.financeiro.controller.usuario;

import com.rosetim.financeiro.entity.usuario.UsuarioEntity;
import com.rosetim.financeiro.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody UsuarioEntity usuarioEntity) throws Exception {
        UsuarioEntity save = usuarioService.save(usuarioEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping
    public ResponseEntity<?> findAll() throws Exception {
        List<UsuarioEntity> all = usuarioService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @GetMapping("/{cdUsuario}")
    public ResponseEntity<?> findById(@PathVariable Long cdUsuario) throws Exception {
        Optional<UsuarioEntity> usuario = usuarioService.findbyId(cdUsuario);

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @DeleteMapping("/{cdUsuario}")
    public ResponseEntity<?> deleteById(@PathVariable Long cdUsuario) throws Exception {
       usuarioService.deleteById(cdUsuario);

       return ResponseEntity.status(HttpStatus.OK).body("Usuário apagado com sucesso");
    }

}
