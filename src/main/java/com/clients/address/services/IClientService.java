package com.clients.address.services;

import java.util.List;

import com.clients.address.model.Client;

/**
 * Client Service Interface.
 * @author Edwin De Los Santos A.
 */
public interface IClientService {

	public List<Client> getClients();
	
	public void save(Client employee);
	
	public Client findClientById(Long id);
	
	public void delete(Long id);
}
