package com.rosetim.financeiro.controller.cartao;

import com.rosetim.financeiro.entity.cartao.CartaoEntity;
import com.rosetim.financeiro.entity.formaPagto.FormaPagtoEntity;
import com.rosetim.financeiro.service.cartao.CartaoService;
import com.rosetim.financeiro.service.formaPagto.FormaPagtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartao")
public class CartaoController {

    @Autowired
    CartaoService cartaoService;

    @PostMapping
    public ResponseEntity save(@RequestBody CartaoEntity cartaoEntity) throws Exception {
        CartaoEntity save = cartaoService.save(cartaoEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping
    public ResponseEntity findAll() throws Exception {
        List<CartaoEntity> all = cartaoService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(all);
    }
}
