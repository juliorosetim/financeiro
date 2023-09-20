package com.rosetim.financeiro.controller.parcelas;

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

    @GetMapping("/parcelas-por-datas/{dtFiltro}")
    public ResponseEntity findParcelasByData(@PathVariable LocalDate dtFiltro) throws Exception {
        LocalDate dtHoje = LocalDate.now();

        Calendar calendar = Calendar.getInstance();
        calendar.set(dtFiltro.getYear(), dtFiltro.getMonthValue() -1,
                10);

        Instant instant = calendar.toInstant();
        LocalDate dtProximaParcela = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        List<ParcelasGastosDto> parcelasByData = parcelasService.findParcelasByData(dtProximaParcela, dtHoje);

        return ResponseEntity.status(HttpStatus.OK).body(parcelasByData);
    }
}
