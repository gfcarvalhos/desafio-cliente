package com.gabrielsilva.cliente.repositories;

import com.gabrielsilva.cliente.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
