package com.gabrielsilva.cliente.services;

import com.gabrielsilva.cliente.dto.ClientDTO;
import com.gabrielsilva.cliente.entities.Client;
import com.gabrielsilva.cliente.repositories.ClientRepository;
import com.gabrielsilva.cliente.services.expections.DatabaseException;
import com.gabrielsilva.cliente.services.expections.NoFoundElementException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Transactional
    public ClientDTO create(ClientDTO dto){
        Client entity = new Client();

        copyDtoToEntity(dto, entity);

        if (repository.existsByCpf(dto.getCpf())) {
            throw new DatabaseException("CPF já cadastrado");
        }
        entity = repository.save(entity);

        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);

            return new ClientDTO(entity);

        } catch(EntityNotFoundException e) {
            throw new NoFoundElementException("Cliente não encontrado");
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)) {
            throw new NoFoundElementException("Cliente não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch(EntityNotFoundException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setChildren(dto.getChildren());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
    }

}
