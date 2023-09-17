package com.rosetim.financeiro.service.parcelas;

import com.rosetim.financeiro.entity.parcelas.ParcelasEntity;
import com.rosetim.financeiro.repository.parcelas.ParcelasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
