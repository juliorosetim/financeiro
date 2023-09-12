package com.rosetim.financeiro.controller.formaPagto;

import com.rosetim.financeiro.entity.formaPagto.FormaPagtoEntity;
import com.rosetim.financeiro.service.formaPagto.FormaPagtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formapagto")
public class FormaPagtoController {

    @Autowired
    FormaPagtoService formaPagtoService;

    @PostMapping
    public ResponseEntity save(@RequestBody FormaPagtoEntity formaPagtoEntity) throws Exception {
        FormaPagtoEntity save = formaPagtoService.save(formaPagtoEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping
    public ResponseEntity findAll() throws Exception {
        List<FormaPagtoEntity> all = formaPagtoService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(all);
    }
}
