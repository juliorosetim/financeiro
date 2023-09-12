package com.rosetim.financeiro.service.gastos;

import com.rosetim.financeiro.entity.cartao.CartaoEntity;
import com.rosetim.financeiro.entity.gastos.GastosEntity;
import com.rosetim.financeiro.repository.gastos.GastosRepository;
import com.rosetim.financeiro.service.cartao.CartaoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class GastosService {

    @Autowired
    GastosRepository gastosRepository;

    @Autowired
    CartaoService cartaoService;

    public void save(GastosEntity gastosEntity) throws Exception {
        try {
            CartaoEntity cartaoEntity = cartaoService.findbyCdCartao(gastosEntity.getCartao().getCdCartao());

            if(gastosEntity.getQtdeParcela() > 1) {
                BigDecimal vlrParcela = gastosEntity.getVlrTotal()
                        .divide(BigDecimal.valueOf(gastosEntity.getQtdeParcela()));

                ArrayList parcelasInserir = new ArrayList();
                for(int i = 1; i <= gastosEntity.getQtdeParcela(); i++){

                    LocalDate dtVencimento = dataVencimento(gastosEntity, cartaoEntity, i);

                    parcelasInserir.add(gastosEntity
                            .builder()
                            .deFatura(gastosEntity.getDeFatura())
                            .deDescricao(gastosEntity.getDeDescricao())
                            .grupo(gastosEntity.getGrupo())
                            .formaPagto(gastosEntity.getFormaPagto())
                            .cartao(gastosEntity.getCartao())
                            .qtdeParcela(gastosEntity.getQtdeParcela())
                            .vlrParcela(vlrParcela)
                            .vlrTotal(gastosEntity.getVlrTotal())
                            .dtVencimento(dtVencimento)
                            .conferido("N")
                            .nuParcela(i)
                            .usuario(gastosEntity.getUsuario())
                            .build());
                }

                gastosRepository.saveAll(parcelasInserir);
            }else{
                gastosEntity.setQtdeParcela(1);

                LocalDate dtVencimento = dataVencimento(gastosEntity, cartaoEntity, gastosEntity.getQtdeParcela());

                gastosEntity.setNuParcela(1);
                gastosEntity.setDtVencimento(dtVencimento);
                gastosRepository.save(gastosEntity);
            }
        }catch (Exception e) {
            throw new Exception(e);
        }
    }

    private static LocalDate dataVencimento(GastosEntity gastosEntity, CartaoEntity cartaoEntity, Integer parcela) {
        Integer diaAtual = diaAtual();

        Date date = convertDate(gastosEntity);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if(cartaoEntity.getDiaVirada() > diaAtual ){
            if(cartaoEntity.getDiaVencimento() > diaAtual) {
                calendar.add(Calendar.MONTH, parcela);
            }else{
                calendar.add(Calendar.MONTH, parcela + 1);
            }
        }else{
            if(cartaoEntity.getDiaVencimento() > diaAtual) {
                calendar.add(Calendar.MONTH, parcela);
            }else{
                calendar.add(Calendar.MONTH, parcela + 1);
            }
        }

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) ,
                cartaoEntity.getDiaVencimento());

        Date novoVencimento = calendar.getTime();

        Instant instant = novoVencimento.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }


    private static Integer diaAtual() {
        Date datAtual = new Date();
        Calendar calendarAtual = Calendar.getInstance();
        calendarAtual.setTime(datAtual);
        return calendarAtual.get(Calendar.DAY_OF_MONTH);
    }

    private static Date convertDate(GastosEntity gastosEntity) {
        Instant instant = gastosEntity.getDtVencimento().atStartOfDay(ZoneId.systemDefault()).toInstant();

        Date date = Date.from(instant);

        return date;
    }

    public List<GastosEntity> findAll() throws Exception {
        try {
            return gastosRepository.findAll();
        }catch (Exception e) {
            throw new Exception(e);
        }
    }
}
