package com.rosetim.financeiro.service.categoria;

import com.rosetim.financeiro.entity.categoria.CategoriaEntity;
import com.rosetim.financeiro.entity.gastos.GastosEntity;
import com.rosetim.financeiro.exception.CustomException;
import com.rosetim.financeiro.repository.categoria.CategoriaRepository;
import com.rosetim.financeiro.repository.gastos.GastosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository repository;

    @Autowired
    GastosRepository gastosRepository;

    public CategoriaEntity save(CategoriaEntity categoria) throws Exception{
        try{
            return repository.save(categoria);
        }catch (Exception e) {
            throw new Exception("Erro ao salvar categoria");
        }
    }

    public List<CategoriaEntity> findAll() throws  Exception{
        try{
            return  repository.findAll();
        }catch (Exception e){
            throw new Exception("Erro ao buscar categorias");
        }
    }

    public void deleteById(Long cdCategoria)throws Exception{
        try{
            Optional<CategoriaEntity> categoria = repository.findById(cdCategoria);

            if(categoria.isPresent()){
                List<GastosEntity> gastoVinculado = gastosRepository.findByCategoria(categoria);

                if(gastoVinculado.size() > 0){
                    throw new CustomException("Categoria vinculada a gastos, não pode ser excluída!");
                }
            }

            repository.deleteById(cdCategoria);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}
