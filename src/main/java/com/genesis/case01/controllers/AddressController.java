package com.genesis.case01.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.case01.domain.Address;
import com.genesis.case01.services.AddressService;

@RestController
@RequestMapping(AddressController.BASE_URL)
public class AddressController {

	public static final String BASE_URL = "/api/v1/Addresses";

	private final AddressService addressService;

	public AddressController(AddressService addressService) {
		super();
		this.addressService = addressService;
	}

	@GetMapping("/{id}")
	public Address findAddressById(@PathVariable Long id) {
		return addressService.findAddressById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Address saveAddress(@RequestBody Address address) {
		return addressService.saveAddress(address);
	}

	@PutMapping("/{id}")
	public Address updateAddress(@PathVariable Long id, @RequestBody Address address) {

		// Search

		Address OldValue = addressService.findAddressById(id);

		// Update
		return addressService.saveAddress(address);

		// return address;
	}

}
