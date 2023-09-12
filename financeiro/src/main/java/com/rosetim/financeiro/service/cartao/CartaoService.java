package com.rosetim.financeiro.service.cartao;

import com.rosetim.financeiro.entity.cartao.CartaoEntity;
import com.rosetim.financeiro.repository.cartao.CartaoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CartaoService {

    @Autowired
    CartaoRepository cartaoRepository;

    public CartaoEntity save(CartaoEntity cartaoEntity) throws Exception {
        try {
            return cartaoRepository.save(cartaoEntity);
        }catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<CartaoEntity> findAll() throws Exception {
        try {
            return cartaoRepository.findAll();
        }catch (Exception e) {
            throw new Exception(e);
        }
    }

    public CartaoEntity findbyCdCartao(Long cdCartao) throws Exception {
        try {
            return cartaoRepository.findByCdCartao(cdCartao);
        }catch (Exception e) {
            throw new Exception(e);
        }
    }
}
