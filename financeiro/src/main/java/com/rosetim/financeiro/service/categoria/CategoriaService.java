package com.rosetim.financeiro.service.categoria;

import com.rosetim.financeiro.entity.categoria.CategoriaEntity;
import com.rosetim.financeiro.repository.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository repository;

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

    public void delete(Long cdCategoria)throws Exception{
        try{
            repository.deleteById(cdCategoria);
        }catch (Exception e){
            throw new Exception("Erro ao excluir categoria");
        }
    }
}
