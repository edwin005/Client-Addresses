package com.clients.address.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clients.address.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	

}

