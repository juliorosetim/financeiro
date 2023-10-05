package com.rosetim.financeiro.service.grupo;

import com.rosetim.financeiro.entity.gastos.GastosEntity;
import com.rosetim.financeiro.entity.grupo.GrupoEntity;
import com.rosetim.financeiro.exception.CustomException;
import com.rosetim.financeiro.repository.gastos.GastosRepository;
import com.rosetim.financeiro.repository.grupo.GrupoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class GrupoService {

    @Autowired
    GrupoRepository grupoRepository;

    @Autowired
    GastosRepository gastosRepository;

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
            Optional<GrupoEntity> grupo = grupoRepository.findById(cdGrupo);

            if(grupo.isPresent()){
                List<GastosEntity> gastoVinculado = gastosRepository.findByGrupo(grupo);

                if(gastoVinculado.size() > 0){
                    throw new CustomException("Grupo vinculado a um gasto, não pode ser excluído!");
                }
            }

            grupoRepository.deleteById(cdGrupo);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}
