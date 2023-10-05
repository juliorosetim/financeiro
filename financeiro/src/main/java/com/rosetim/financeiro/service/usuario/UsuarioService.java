package com.rosetim.financeiro.service.usuario;

import com.rosetim.financeiro.entity.gastos.GastosEntity;
import com.rosetim.financeiro.entity.usuario.UsuarioEntity;
import com.rosetim.financeiro.exception.CustomException;
import com.rosetim.financeiro.repository.gastos.GastosRepository;
import com.rosetim.financeiro.repository.ususario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final GastosRepository gastosRepository;

    public UsuarioEntity save(UsuarioEntity usuarioEntity) throws Exception {
        try {
            return usuarioRepository.save(usuarioEntity);
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public List<UsuarioEntity> findAll()throws Exception{
        try{
            return usuarioRepository.findAll();
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public Optional<UsuarioEntity> findbyId(Long cdUsuario)throws Exception{
        try{
            return usuarioRepository.findById(cdUsuario);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public void deleteById(Long cdUsuario)throws Exception{
        try{
            Optional<UsuarioEntity> usuario = usuarioRepository.findById(cdUsuario);

            if(usuario.isPresent()){
                List<GastosEntity> gastoVinculado = gastosRepository.findByUsuario(usuario);

                if(gastoVinculado.size() > 0){
                    throw new CustomException("Usuario vinculado a um gasto, não pode ser excluído!");
                }
            }

            usuarioRepository.deleteById(cdUsuario);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

}
