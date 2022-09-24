package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ClienteLoginDTO;
import com.indracompany.treinamento.model.entity.ClienteLogin;
import com.indracompany.treinamento.model.repository.ClientLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientLoginService extends GenericCrudService<ClienteLogin, Long, ClientLoginRepository>{

    public final ClientLoginRepository loginRepository;

    public String createCLient(ClienteLogin clienteLogin){
       try {
           String encoder = new BCryptPasswordEncoder().encode(clienteLogin.getPassword());
           clienteLogin.setPassword(encoder);
           loginRepository.save(clienteLogin);
       } catch (Exception e){
           return null;
       }
        return "User created with success!";
    }

    public ClienteLoginDTO authenticateClient(ClienteLoginDTO login){
        ClienteLogin client = loginRepository.findByUsername(login.getUsername())
                .orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ERRO_VALIDACAO));

        boolean passTest = new BCryptPasswordEncoder().matches(login.getPassword(), client.getPassword());
        if(passTest){
            return login;
        }
        return null;
    }
}
