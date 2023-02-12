package com.clients.address.services;

import java.util.List;

import com.clients.address.model.Address;

/**
 * Address Service Interface.
 * @author Edwin De Los Santos A.
 */
public interface IAddressService {
	
	public List<Address> getClientAddress();
	
	public void save(Address address);
	
	public Address findAddressById(Long id);
	
	public void delete(Long id);
}

