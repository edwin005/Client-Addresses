package com.clients.address.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clients.address.model.Address;
import com.clients.address.repository.AddressRepository;


@Service
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public List<Address> getClientAddress() {
		
		return (List<Address>)addressRepository.findAll();
	}

	@Override
	public void save(Address address) {
		addressRepository.save(address);
	}

	@Override
	public Address findAddressById(Long id) {
		return addressRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		addressRepository.deleteById(id);
	}
	

}
