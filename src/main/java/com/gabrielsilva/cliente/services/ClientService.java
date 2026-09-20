package com.gabrielsilva.cliente.services;

import com.gabrielsilva.cliente.dto.ClientDTO;
import com.gabrielsilva.cliente.entities.Client;
import com.gabrielsilva.cliente.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> result = repository.findById(id);
        Client cliente = result.get();
        return new ClientDTO(cliente);
    }
}
