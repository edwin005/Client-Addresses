package com.clients.address.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clients.address.model.Client;
import com.clients.address.repository.ClientRepository;


@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public List<Client> getClients() {
		return (List<Client>) clientRepository.findAll();
	}

	@Override
	public void save(Client client) {
		clientRepository.save(client);
	}

	@Override
	public Client findClientById(Long id) {
		return clientRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		clientRepository.deleteById(id);
	}
}
