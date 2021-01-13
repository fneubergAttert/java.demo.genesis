/**
 * 
 */
package com.genesis.case01.services;

import org.springframework.stereotype.Service;

import com.genesis.case01.domain.Address;
import com.genesis.case01.respositories.AddressRepository;

/**
 * @author fred_
 *
 */
@Service
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;

	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public Address findAddressById(Long id) {

		Address exAddress = addressRepository.findById(id).orElse(null);

		return exAddress;

	}

	@Override
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}

}
