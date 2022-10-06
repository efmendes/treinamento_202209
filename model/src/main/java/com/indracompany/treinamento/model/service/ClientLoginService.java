package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ClienteLoginDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ClienteLogin;
import com.indracompany.treinamento.model.repository.ClientLoginRepository;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientLoginService extends GenericCrudService<ClienteLogin, Long, ClientLoginRepository>{

    public final ClientLoginRepository loginRepository;
    public final ClienteRepository clienteRepository;

    @Transactional(rollbackFor = Exception.class)
    public ClienteLogin createCLient(ClienteLogin clienteLogin){
       try {
           String encoder = new BCryptPasswordEncoder().encode(clienteLogin.getPassword());
           clienteLogin.setPassword(encoder);

           loginRepository.saveAndFlush(clienteLogin);


       } catch (Exception e){

           return null;
       }
        return clienteLogin;
    }

    public ClienteLoginDTO authenticateClient(ClienteLoginDTO login){
        ClienteLogin client = loginRepository.findByUsername(login.getUsername())
                .orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ERRO_VALIDACAO));

        String[] firstName = login.getUsername().split(" ");
        login.setUsername(firstName[0]);

        boolean passTest = new BCryptPasswordEncoder().matches(login.getPassword(), client.getPassword());

        login.setCliente(client.getCliente());
        if(passTest){

            return login;
        }
        return null;
    }
}
