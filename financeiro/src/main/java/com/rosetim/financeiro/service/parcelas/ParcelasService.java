package com.rosetim.financeiro.service.parcelas;

import com.rosetim.financeiro.dto.ParcelasGastosDto;
import com.rosetim.financeiro.entity.parcelas.ParcelasEntity;
import com.rosetim.financeiro.repository.parcelas.ParcelasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParcelasService {

    @Autowired
    ParcelasRepository parcelasRepository;

    public List<ParcelasEntity> save(List<ParcelasEntity> parcelasEntity) throws Exception {
        try {
            return parcelasRepository.saveAll(parcelasEntity);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public List<ParcelasEntity> findAllParcelas() throws Exception {
        try{
            return parcelasRepository.findAll();
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public List<ParcelasGastosDto> findParcelasByData(LocalDate dtProximaParcela, LocalDate dtHoje) throws Exception {
        try {
            List<Object[]> parcelas = parcelasRepository.findParcelasByData(dtProximaParcela, dtHoje);

            List<ParcelasGastosDto> dtos = new ArrayList<>();
            for (Object[] result : parcelas) {
                ParcelasGastosDto dto = new ParcelasGastosDto();
                dto.setDeFatura((String) result[0]);
                dto.setDeDescricao((String) result[1]);
                dto.setCdParcela(((ParcelasEntity) result[2]).getCdparcela());
                dto.setCdGasto(((ParcelasEntity) result[2]).getCdGasto());
                dto.setQtdeParcela(((ParcelasEntity) result[2]).getQtdeParcela());
                dto.setVlrParcela(((ParcelasEntity) result[2]).getVlrParcela());
                dto.setVlrtotal(((ParcelasEntity) result[2]).getVlrTotal());
                dto.setDtVencimento(((ParcelasEntity) result[2]).getDtVencimento());
                dto.setConferido(((ParcelasEntity) result[2]).getConferido());
                dto.setNuParcela(((ParcelasEntity) result[2]).getNuParcela());
                dtos.add(dto);
            }

            return dtos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
