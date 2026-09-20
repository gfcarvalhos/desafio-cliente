package com.gabrielsilva.cliente.services;

import com.gabrielsilva.cliente.dto.ClientDTO;
import com.gabrielsilva.cliente.entities.Client;
import com.gabrielsilva.cliente.repositories.ClientRepository;
import com.gabrielsilva.cliente.services.expections.NoFoundElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = repository.findById(id)
                .orElseThrow(() -> new NoFoundElementException("Cliente não encontrado"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pegeable){
        Page<Client> result = repository.findAll(pegeable);
        return result.map(ClientDTO::new);
    }
}
