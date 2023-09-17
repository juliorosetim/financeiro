package com.rosetim.financeiro.service.grupo;

import com.rosetim.financeiro.entity.grupo.GrupoEntity;
import com.rosetim.financeiro.repository.grupo.GrupoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class GrupoService {

    @Autowired
    GrupoRepository grupoRepository;

    public GrupoEntity save(GrupoEntity grupoEntity) throws Exception {
        try {
            return grupoRepository.save(grupoEntity);
        }catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<GrupoEntity> findAll() throws Exception {
        try {
            return grupoRepository.findAll();
        }catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void deleteById(Long cdGrupo)throws Exception{
        try{
            grupoRepository.deleteById(cdGrupo);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}
