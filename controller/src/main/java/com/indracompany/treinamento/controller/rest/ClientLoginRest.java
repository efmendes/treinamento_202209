package com.indracompany.treinamento.controller.rest;

import com.indracompany.treinamento.model.dto.ClienteLoginDTO;
import com.indracompany.treinamento.model.entity.ClienteLogin;
import com.indracompany.treinamento.model.service.ClientLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/login")
@RequiredArgsConstructor
public class ClientLoginRest extends GenericCrudRest<ClienteLogin, Long, ClientLoginService>{

    private final ClientLoginService loginService;

    @PostMapping
    public ResponseEntity<ClienteLogin> addClient(@RequestBody ClienteLogin clienteLogin){
        ClienteLogin answer =  loginService.createCLient(clienteLogin);
        if(answer == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ClienteLoginDTO> authenticate(@RequestBody ClienteLoginDTO login){
        ClienteLoginDTO answer = loginService.authenticateClient(login);
        if(answer == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().body(answer);
    }
}
