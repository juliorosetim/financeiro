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

    public void deleteParcelaByCdGasto(Long cdGasto) throws Exception{
        try{
            List<ParcelasEntity> parcelas = parcelasRepository.findByCdGasto(cdGasto);

            for(ParcelasEntity parcela : parcelas) {
                parcelasRepository.deleteById(parcela.getCdParcela());
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<ParcelasGastosDto> findParcelasByData(LocalDate dtProximaParcela, LocalDate dtHoje) throws Exception {
        try {
            List<Object[]> parcelas = parcelasRepository.findParcelasByData(dtProximaParcela, dtHoje);

            List<ParcelasGastosDto> dtos = new ArrayList<>();
            for (Object[] result : parcelas) {
                ParcelasGastosDto dto = new ParcelasGastosDto();
                dto.setDeCartao((String) result[0]);
                dto.setDeFatura((String) result[1]);
                dto.setDeDescricao((String) result[2]);
                dto.setDeCategoria((String) result[3]);
                dto.setCdParcela(((ParcelasEntity) result[4]).getCdParcela());
                dto.setCdGasto(((ParcelasEntity) result[4]).getCdGasto());
                dto.setQtdeParcela(((ParcelasEntity) result[4]).getQtdeParcela());
                dto.setVlrParcela(((ParcelasEntity) result[4]).getVlrParcela());
                dto.setVlrtotal(((ParcelasEntity) result[4]).getVlrTotal());
                dto.setDtVencimento(((ParcelasEntity) result[4]).getDtVencimento());
                dto.setConferido(((ParcelasEntity) result[4]).getConferido());
                dto.setNuParcela(((ParcelasEntity) result[4]).getNuParcela());
                dtos.add(dto);
            }

            return dtos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<ParcelasGastosDto> findParcelasByGasto(Long cdGasto) throws Exception {
        try {
            List<Object[]> parcelas = parcelasRepository.findParcelasByCdGasto(cdGasto);

            List<ParcelasGastosDto> dtos = new ArrayList<>();
            for (Object[] result : parcelas) {
                ParcelasGastosDto dto = new ParcelasGastosDto();
                dto.setDeCartao((String) result[0]);
                dto.setDeFatura((String) result[1]);
                dto.setDeDescricao((String) result[2]);
                dto.setDeCategoria((String) result[3]);
                dto.setCdParcela(((ParcelasEntity) result[4]).getCdParcela());
                dto.setCdGasto(((ParcelasEntity) result[4]).getCdGasto());
                dto.setQtdeParcela(((ParcelasEntity) result[4]).getQtdeParcela());
                dto.setVlrParcela(((ParcelasEntity) result[4]).getVlrParcela());
                dto.setVlrtotal(((ParcelasEntity) result[4]).getVlrTotal());
                dto.setDtVencimento(((ParcelasEntity) result[4]).getDtVencimento());
                dto.setConferido(((ParcelasEntity) result[4]).getConferido());
                dto.setNuParcela(((ParcelasEntity) result[4]).getNuParcela());
                dtos.add(dto);
            }

            return dtos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
