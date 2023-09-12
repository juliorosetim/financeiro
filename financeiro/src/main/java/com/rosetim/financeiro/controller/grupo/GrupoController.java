package com.rosetim.financeiro.controller.grupo;

import com.rosetim.financeiro.entity.cartao.CartaoEntity;
import com.rosetim.financeiro.entity.grupo.GrupoEntity;
import com.rosetim.financeiro.service.cartao.CartaoService;
import com.rosetim.financeiro.service.grupo.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupo")
public class GrupoController {

    @Autowired
    GrupoService grupoService;

    @PostMapping
    public ResponseEntity save(@RequestBody GrupoEntity grupoEntity) throws Exception {
        GrupoEntity save = grupoService.save(grupoEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping
    public ResponseEntity findAll() throws Exception {
        List<GrupoEntity> all = grupoService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(all);
    }
}
