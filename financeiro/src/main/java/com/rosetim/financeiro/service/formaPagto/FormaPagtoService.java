package com.rosetim.financeiro.service.formaPagto;

import com.rosetim.financeiro.entity.formaPagto.FormaPagtoEntity;
import com.rosetim.financeiro.repository.formapagto.FormaPagtoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagtoService {

    @Autowired
    FormaPagtoRepository formaPagtoRepository;

    public FormaPagtoEntity save(FormaPagtoEntity formaPagtoEntity) throws Exception {
        try {
            return formaPagtoRepository.save(formaPagtoEntity);
        }catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<FormaPagtoEntity> findAll() throws Exception {
        try {
            return formaPagtoRepository.findAll();
        }catch (Exception e) {
            throw new Exception(e);
        }
    }
}
