package com.rosetim.financeiro.service.gastos;

import com.rosetim.financeiro.entity.cartao.CartaoEntity;
import com.rosetim.financeiro.entity.gastos.GastosEntity;
import com.rosetim.financeiro.entity.parcelas.ParcelasEntity;
import com.rosetim.financeiro.repository.gastos.GastosRepository;
import com.rosetim.financeiro.service.cartao.CartaoService;
import com.rosetim.financeiro.service.parcelas.ParcelasService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
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
    ParcelasService parcelasService;

    @Autowired
    CartaoService cartaoService;

    @Transactional(rollbackFor = Throwable.class)
    public void save(GastosEntity gastosEntity) throws Exception {
        try {
            if (!Objects.isNull(gastosEntity.getCartao())) {
                CartaoEntity cartaoEntity = cartaoService.findbyCdCartao(gastosEntity.getCartao().getCdCartao());

                if (Objects.isNull(cartaoEntity)){
                    gastosEntity.setCartao(null);
                };
            }

            if ( Objects.isNull(gastosEntity.getQtdeParcela())){
                gastosEntity.setQtdeParcela(1);
            }

            GastosEntity gastoSave = gastosRepository.save(gastosEntity);

            ArrayList parcelasInserir = new ArrayList();
            for(int i = 1; i <= gastosEntity.getQtdeParcela(); i++){
                BigDecimal vlrParcela = gastosEntity.getVlrTotal()
                        .divide(BigDecimal.valueOf(gastosEntity.getQtdeParcela()), MathContext.DECIMAL64);

                LocalDate dtVencimento = dataVencimento(gastosEntity.getDtLancamento(),
                        gastosEntity.getDtLancamento().getDayOfMonth(), i);
                //LocalDate dtVencimento = dataVencimento(cartaoEntity, i);

                parcelasInserir.add(ParcelasEntity
                        .builder()
                        .cdGasto(gastoSave.getCdGasto())
                        .qtdeParcela(gastosEntity.getQtdeParcela())
                        .vlrParcela(vlrParcela)
                        .vlrTotal(gastoSave.getVlrTotal())
                        .dtVencimento(dtVencimento)
                        .conferido("N")
                        .nuParcela(i)
                        .build());
            }

            if (parcelasInserir.size() >0){
                parcelasService.save(parcelasInserir);
            }

        }catch (Exception e) {
            throw new Exception(e);
        }
    }

    private static LocalDate dataVencimento(LocalDate dataVencimento, Integer diaVencimento, Integer parcela) {
        Date date = convertDate(dataVencimento);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if(parcela > 1 ){
            calendar.add(Calendar.MONTH, parcela -1);
        }

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                diaVencimento);

        Date novoVencimento = calendar.getTime();

        Instant instant = novoVencimento.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

//---------QUASE ---------------------
//    private static LocalDate dataVencimento(LocalDate dtLancamento, CartaoEntity cartaoEntity, Integer parcela) {
//        LocalDate dataVencimento = getData(cartaoEntity.getDiaVencimento(),9,2023); // sempre recebe a data atual
//        LocalDate dataVirada = getData(cartaoEntity.getDiaVirada(), 9,2023);
//        LocalDate dataLancamento = getData(25,9,2023);
//
////        LocalDate dataVencimento = getDataVencimento(cartaoEntity.getDiaVencimento());
////
////        LocalDate dataVirada = getDataVirada(cartaoEntity.getDiaVirada());
////
////        LocalDate dataLancamento = LocalDate.now();
////        if (!Objects.isNull(dtLancamento)) {
////            dataLancamento = dtLancamento;
////        }
//
//        Date date = convertDate(dataVencimento);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//
//        if(dataLancamento.isAfter(dataVirada) || dataLancamento.isEqual(dataVirada) ){
//            if(dataLancamento.isBefore(dataVencimento) || dataLancamento.isEqual(dataVencimento) ){
//                calendar.add(Calendar.MONTH, parcela);
//            }else{
//                calendar.add(Calendar.MONTH, parcela + 1);
//            }
//        }
//        else{
//            calendar.add(Calendar.MONTH, parcela);
//        }
//
//        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) ,
//                cartaoEntity.getDiaVencimento());
//
//        Date novoVencimento = calendar.getTime();
//
//        Instant instant = novoVencimento.toInstant();
//        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
//        return localDate;
//    }


//    private static LocalDate dataVencimento(LocalDate dtLancamento, CartaoEntity cartaoEntity, Integer parcela) {
//        LocalDate dataVencimento = getData(cartaoEntity.getDiaVencimento(),10,2023); // sempre recebe a data atual
//        LocalDate dataVirada = getData(cartaoEntity.getDiaVirada(), 10,2023);
//        LocalDate dataLancamento = getData(05,10,2023);
//        LocalDate dataProximoVencimento = getProximaDataVencimento(dataVencimento, cartaoEntity.getDiaVencimento());
//        LocalDate dataProximaVirada = getProximaVirada(dataVirada, cartaoEntity.getDiaVirada());
//
////        LocalDate dataVencimento = getDataVencimento(cartaoEntity.getDiaVencimento());
////
////        LocalDate dataVirada = getDataVirada(cartaoEntity.getDiaVirada());
////
////        LocalDate dataLancamento = LocalDate.now();
////        if (!Objects.isNull(dtLancamento)) {
////            dataLancamento = dtLancamento;
////        }
//
//        Date date = convertDate(dataVencimento);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//
////        if(dataLancamento.isBefore(dataVirada) || dataLancamento.isEqual(dataVirada)){
////            calendar.add(Calendar.MONTH, parcela);
////        }
//
//        if((dataLancamento.isAfter(dataVirada) || dataLancamento.isEqual(dataVirada)) &&
//           (dataLancamento.getMonth().getValue() != dataProximoVencimento.getMonth().getValue())){
//            calendar.add(Calendar.MONTH, parcela + 1);
//        }
//
//        if((dataLancamento.isAfter(dataVirada) || dataLancamento.isEqual(dataVirada)) &&
//                (dataLancamento.getMonth().getValue() == dataProximoVencimento.getMonth().getValue())){
//            calendar.add(Calendar.MONTH, parcela);
//        }
//
//        if((dataLancamento.isBefore(dataVirada) || dataLancamento.isEqual(dataVirada)) &&
//                (dataLancamento.getMonth().getValue() == dataProximoVencimento.getMonth().getValue())){
//            calendar.add(Calendar.MONTH, parcela + 1);
//        }
//
//
//
////        if( (dataLancamento.isAfter(dataVirada) || dataLancamento.isEqual(dataVirada)) &&
////                (dataLancamento.isBefore(dataProximaVirada) || dataLancamento.isEqual(dataProximaVirada))){
////            calendar.add(Calendar.MONTH, parcela);
////        }
//
//
////        if((dataLancamento.isAfter(dataVirada) || dataLancamento.isEqual(dataVirada)) ||
////                (dataLancamento.isAfter(dataProximaVirada) || dataLancamento.isEqual(dataProximaVirada)) &&
////                (dataLancamento.isAfter(dataVencimento) && dataLancamento.isAfter(dataProximoVencimento) )){
////                calendar.add(Calendar.MONTH, parcela);
////        }else{
////            calendar.add(Calendar.MONTH, parcela + 1);
////        }
//
//        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) ,
//                cartaoEntity.getDiaVencimento());
//
//        Date novoVencimento = calendar.getTime();
//
//        Instant instant = novoVencimento.toInstant();
//        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
//        return localDate;
//    }

    private static LocalDate getData(Integer dia, Integer mes, Integer ano){
        Date datAtual = new Date();
        Calendar calendarAtual = Calendar.getInstance();

        calendarAtual.set(ano, mes -1,
                dia);

        Date novoVencimento = calendarAtual.getTime();
        Instant instant = novoVencimento.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        return localDate;
    }

    private static LocalDate getDataVencimento(Integer diaVencimento){
        Date datAtual = new Date();
        Calendar calendarAtual = Calendar.getInstance();

        calendarAtual.set(calendarAtual.get(Calendar.YEAR), calendarAtual.get(Calendar.MONTH),
                diaVencimento);

        Date novoVencimento = calendarAtual.getTime();
        Instant instant = novoVencimento.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        return localDate;
    }

    private static LocalDate getProximaDataVencimento(LocalDate vencimento, Integer diaVencimento){
       // Date datAtual = new Date();
        Calendar calendarAtual = Calendar.getInstance();

        calendarAtual.set(vencimento.getYear(), vencimento.getMonthValue(),
                diaVencimento);

        Date novoVencimento = calendarAtual.getTime();
        Instant instant = novoVencimento.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        return localDate;
    }

    private static LocalDate getDataVirada(Integer diaVirada){
        Date datAtual = new Date();
        Calendar calendarAtual = Calendar.getInstance();

        calendarAtual.set(calendarAtual.get(Calendar.YEAR), calendarAtual.get(Calendar.MONTH),
               diaVirada);

        Date novoVencimento = calendarAtual.getTime();
        Instant instant = novoVencimento.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        return localDate;
    }

    private static LocalDate getProximaVirada(LocalDate virada, Integer diaVirada){
        Calendar calendarAtual = Calendar.getInstance();

        calendarAtual.set(virada.getYear(), virada.getMonthValue(),
                diaVirada);

        Date novaVirada = calendarAtual.getTime();
        Instant instant = novaVirada.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        return localDate;
    }

    private static Integer diaAtual() {
        Date datAtual = new Date();
        Calendar calendarAtual = Calendar.getInstance();
        calendarAtual.setTime(datAtual);
        return calendarAtual.get(Calendar.DAY_OF_MONTH);
    }

    private static Date convertDate(LocalDate dataVencimento) {
        Instant instant = dataVencimento.atStartOfDay(ZoneId.systemDefault()).toInstant();

        Date date = Date.from(instant);

        return date;
    }


    @Transactional(rollbackFor = Throwable.class)
    public void update(GastosEntity gastosEntity) throws Exception {
        try {
            CartaoEntity cartaoEntity = cartaoService.findbyCdCartao(gastosEntity.getCartao().getCdCartao());

            if ( Objects.isNull(gastosEntity.getQtdeParcela())){
                gastosEntity.setQtdeParcela(1);
            }

            GastosEntity gastoSave = gastosRepository.save(gastosEntity);

            parcelasService.deleteParcelaByCdGasto(gastoSave.getCdGasto());

            ArrayList parcelasInserir = new ArrayList();
            for(int i = 1; i <= gastosEntity.getQtdeParcela(); i++){
                BigDecimal vlrParcela = gastosEntity.getVlrTotal()
                        .divide(BigDecimal.valueOf(gastosEntity.getQtdeParcela()), MathContext.DECIMAL64);

                LocalDate dtVencimento = dataVencimento(gastosEntity.getDtLancamento(),
                        gastosEntity.getDtLancamento().getDayOfMonth(), i);
                //LocalDate dtVencimento = dataVencimento(cartaoEntity, i);

                parcelasInserir.add(ParcelasEntity
                        .builder()
                        .cdGasto(gastoSave.getCdGasto())
                        .qtdeParcela(gastosEntity.getQtdeParcela())
                        .vlrParcela(vlrParcela)
                        .vlrTotal(gastoSave.getVlrTotal())
                        .dtVencimento(dtVencimento)
                        .conferido("N")
                        .nuParcela(i)
                        .pago(gastoSave.getPago())
                        .build());
            }

            if (parcelasInserir.size() >0){
                parcelasService.save(parcelasInserir);
            }

        }catch (Exception e) {
            throw new Exception(e);
        }
    }


    public List<GastosEntity> findAll() throws Exception {
        try {
            List<GastosEntity> gastos = gastosRepository.findAll();
            gastos.sort((g1, g2) -> Math.toIntExact(g2.getCdGasto() - g1.getCdGasto()));
            return gastos;
        }catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void deleteById(Long cdGasto) throws Exception {
        try{
            parcelasService.deleteParcelaByCdGasto(cdGasto);

            gastosRepository.deleteById(cdGasto);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
