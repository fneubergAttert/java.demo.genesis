package com.genesis.case01.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.genesis.case01.domain.Address;


public interface AddressService {

	
	public Address findAddressById(Long id);
	

	public Address saveAddress(Address Address);


	
}
