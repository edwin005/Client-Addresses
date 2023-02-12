package com.clients.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clients.address.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{ 

}
