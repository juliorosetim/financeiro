package com.rosetim.financeiro.controller.parcelas;

import com.rosetim.financeiro.dto.GastosAgrupadosDto;
import com.rosetim.financeiro.dto.ParcelasGastosDto;
import com.rosetim.financeiro.entity.parcelas.ParcelasEntity;
import com.rosetim.financeiro.service.parcelas.ParcelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/parcelas")
public class ParcelasController {

    @Autowired
    ParcelasService parcelasService;

    @GetMapping
    public ResponseEntity findAllParcelas() throws Exception {
        List<ParcelasEntity> allParcelas = parcelasService.findAllParcelas();

        return ResponseEntity.status(HttpStatus.OK).body(allParcelas);
    }

    @GetMapping("/parcelas-por-datas/{dtFiltroInicio}/{dtFiltroFim}")
    public ResponseEntity findParcelasByData(@PathVariable LocalDate dtFiltroInicio, @PathVariable LocalDate dtFiltroFim) throws Exception {
        List<ParcelasGastosDto> parcelasByData = parcelasService.findParcelasByData(dtFiltroInicio, dtFiltroFim);

        return ResponseEntity.status(HttpStatus.OK).body(parcelasByData);
    }

    @GetMapping("/parcelas-por-gasto/{cdGasto}")
    public ResponseEntity findParcelasByGasto(@PathVariable Long cdGasto) throws Exception {

        List<ParcelasGastosDto> parcelasByData = parcelasService.findParcelasByGasto(cdGasto);

        return ResponseEntity.status(HttpStatus.OK).body(parcelasByData);
    }

    @GetMapping("/totais-por-cartoes/{dtFiltroInicio}/{dtFiltroFim}")
    public ResponseEntity totaisPorCartoes(@PathVariable LocalDate dtFiltroInicio, @PathVariable LocalDate dtFiltroFim) throws Exception {
        List<GastosAgrupadosDto> gastosAgrupadosDtos = parcelasService.gastosAgrupadosPorCartao(dtFiltroInicio, dtFiltroFim);

        return ResponseEntity.status(HttpStatus.OK).body(gastosAgrupadosDtos);
    }

    @GetMapping("/totais-por-forma-pgto/{dtFiltroInicio}/{dtFiltroFim}")
    public ResponseEntity totaisPorFormaPgto(@PathVariable LocalDate dtFiltroInicio, @PathVariable LocalDate dtFiltroFim) throws Exception {
        List<GastosAgrupadosDto> gastosAgrupadosDtos = parcelasService.gastosAgrupadosPorFormaPgto(dtFiltroInicio, dtFiltroFim);

        return ResponseEntity.status(HttpStatus.OK).body(gastosAgrupadosDtos);
    }

    @GetMapping("/totais-por-categorias/{dtFiltroInicio}/{dtFiltroFim}")
    public ResponseEntity totaisPorCategorias(@PathVariable LocalDate dtFiltroInicio, @PathVariable LocalDate dtFiltroFim) throws Exception {
        List<GastosAgrupadosDto> gastosAgrupadosDtos = parcelasService.gastosAgrupadosPorCategoria(dtFiltroInicio, dtFiltroFim);

        return ResponseEntity.status(HttpStatus.OK).body(gastosAgrupadosDtos);
    }

    @GetMapping("/totais-por-grupo/{dtFiltroInicio}/{dtFiltroFim}")
    public ResponseEntity totaisPorGrupo(@PathVariable LocalDate dtFiltroInicio, @PathVariable LocalDate dtFiltroFim) throws Exception {
        List<GastosAgrupadosDto> gastosAgrupadosDtos = parcelasService.gastosAgrupadosPorGrupo(dtFiltroInicio, dtFiltroFim);

        return ResponseEntity.status(HttpStatus.OK).body(gastosAgrupadosDtos);
    }

    @GetMapping("/totais-por-datas/{dtFiltroInicio}/{dtFiltroFim}")
    public ResponseEntity totaisPorDatas(@PathVariable LocalDate dtFiltroInicio, @PathVariable LocalDate dtFiltroFim) throws Exception {
        List<GastosAgrupadosDto> gastosAgrupadosDtos = parcelasService.gastosAgrupadosPorDatas(dtFiltroInicio, dtFiltroFim);

        return ResponseEntity.status(HttpStatus.OK).body(gastosAgrupadosDtos);
    }

}
