package com.rosetim.financeiro.service.formaPagto;

import com.rosetim.financeiro.entity.formaPagto.FormaPagtoEntity;
import com.rosetim.financeiro.entity.gastos.GastosEntity;
import com.rosetim.financeiro.exception.CustomException;
import com.rosetim.financeiro.repository.formapagto.FormaPagtoRepository;
import com.rosetim.financeiro.repository.gastos.GastosRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagtoService {

    @Autowired
    FormaPagtoRepository formaPagtoRepository;

    @Autowired
    GastosRepository gastosRepository;

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

    public void deleteById(Long cdFormaPagto)throws Exception{
        try{
            Optional<FormaPagtoEntity> formaPagto = formaPagtoRepository.findById(cdFormaPagto);

            if(formaPagto.isPresent()){
                List<GastosEntity> gastosVinculados = gastosRepository.findByFormaPagto(formaPagto);

                if (gastosVinculados.size() > 0){
                    throw new CustomException("Forma de pagamento vinculada a um gasto, não pode ser excluída!");
                }
            }


            formaPagtoRepository.deleteById(cdFormaPagto);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}
