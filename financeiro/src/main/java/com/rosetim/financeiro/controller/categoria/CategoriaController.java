package com.rosetim.financeiro.controller.categoria;

import com.rosetim.financeiro.entity.categoria.CategoriaEntity;
import com.rosetim.financeiro.service.categoria.CategoriaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity save(@RequestBody CategoriaEntity categoria) throws Exception {
        CategoriaEntity categoriaSaved = categoriaService.save(categoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSaved);
    }

    @GetMapping
    public ResponseEntity findAll() throws Exception {
        List<CategoriaEntity> categorias = categoriaService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(categorias);
    }

    @DeleteMapping("/{cdCategoria}")
    public ResponseEntity delete(@PathVariable Long cdCategoria) throws Exception {
        categoriaService.deleteById(cdCategoria);

        return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada com sucesso!");
    }
}
