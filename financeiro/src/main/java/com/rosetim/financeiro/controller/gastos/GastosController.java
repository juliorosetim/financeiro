package com.rosetim.financeiro.controller.gastos;

import com.rosetim.financeiro.entity.gastos.GastosEntity;
import com.rosetim.financeiro.service.gastos.GastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gastos")
public class GastosController {

    @Autowired
    GastosService gastosService;

    @PostMapping
    public ResponseEntity save(@RequestBody GastosEntity gastosEntity) throws Exception {
        gastosService.save(gastosEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body("Gastos salvos com sucesso!");
    }

    @PutMapping
    public ResponseEntity update(@RequestBody GastosEntity gastosEntity) throws Exception {
        gastosService.update(gastosEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body("Gasto atualizado com sucesso!");
    }

    @GetMapping
    public ResponseEntity findAll() throws Exception {
        List<GastosEntity> all = gastosService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @DeleteMapping("/{cdGasto}")
    public ResponseEntity<?> deleteById(@PathVariable Long cdGasto) throws Exception {
        gastosService.deleteById(cdGasto);

        return ResponseEntity.status(HttpStatus.OK).body("Gasto apagado com sucesso");
    }
}
