package com.rosetim.financeiro.service.cartao;

import com.rosetim.financeiro.entity.cartao.CartaoEntity;
import com.rosetim.financeiro.entity.gastos.GastosEntity;
import com.rosetim.financeiro.exception.CustomException;
import com.rosetim.financeiro.repository.cartao.CartaoRepository;
import com.rosetim.financeiro.repository.gastos.GastosRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CartaoService {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    GastosRepository gastosRepository;

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

    public void deleteById(Long cdCartao)throws Exception{
        try{
            CartaoEntity cartao = cartaoRepository.findByCdCartao(cdCartao);

            List<GastosEntity> gastosVinculados = gastosRepository.findByCartao(cartao);

            if (gastosVinculados.size() <= 0){
                cartaoRepository.deleteById(cdCartao);
            }else {
                throw new CustomException("Cartão vinculado a um gasto, não pode ser excluido");
            }

        }catch (Exception e){
            throw new Exception(e);
        }
    }
}
