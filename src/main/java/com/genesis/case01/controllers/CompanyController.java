package com.genesis.case01.controllers;

import java.util.List;

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
import com.genesis.case01.domain.Company;
import com.genesis.case01.exceptions.GenesisNotValideDataException;
import com.genesis.case01.services.AddressService;
import com.genesis.case01.services.CompanyService;

@RestController
@RequestMapping(CompanyController.BASE_URL)
public class CompanyController {

	public static final String BASE_URL = "/api/v1/Companies";

	private final CompanyService CompanyService;
	private final AddressService AddressService;

	public CompanyController(CompanyService CompanyService, AddressService AddressService) {
		super();
		this.CompanyService = CompanyService;
		this.AddressService = AddressService;
	}

	// GET /objects/ Gets all Objects
	@GetMapping
	public List<Company> findAllCompanys() {
		return CompanyService.findAllCompanys();
	}

	// GET /object/ID Gets an Object with specified ID
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Company findCompanyById(@PathVariable Long id) {

		Company company = CompanyService.findCompanyById(id);
		if (company == null) {
			throw new GenesisNotValideDataException("Company not found !");
		}

		return company;
	}

	// POST /objects Adds a new Object
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Company saveCompany(@RequestBody Company pCompany) {

		// Validation
		if (pCompany == null) {
			throw new GenesisNotValideDataException("Data not valided - NULL");
		}

		// Main Addresse
		if (pCompany.getMainAddressCompany() == null) {
			throw new GenesisNotValideDataException("Data not valided - MainAddress :: NULL");

		} else {
			// Validate Addrees
			if (1 == 1) {

			}
		}

		// HeadOffpCompanyice Addresse
		if (pCompany.getMainAddressHeadOffice() == null) {
			throw new GenesisNotValideDataException("Data not valided - AddressHeadOffice :: NULL");

		} else {
			// Validate Addrees
			if (1 == 1) {

			}
		}

		// Manual creation
		{

			// -->
			// Main Adresse
			Address AddressCompany = AddressService.saveAddress(pCompany.getMainAddressCompany());
			pCompany.addAddressMain(AddressCompany);

			// HeadOffpCompanyice
			Address AddressHeadOffice = AddressService.saveAddress(pCompany.getMainAddressHeadOffice());
			pCompany.addAddressHeadOffice(AddressHeadOffice);

		}

		// Validataion

		if (1 == 0) {
			throw new GenesisNotValideDataException("Data not valided - NULL");
		}

		return CompanyService.saveCompany(pCompany);
	}

	// PUT /object/ID Adds an Object with specified ID, Updates an Object
	@PutMapping("/{id}")
	public Company updateCompany(@PathVariable Long id, @RequestBody Company CompanyToUpdate) {

		// Validation
		if (CompanyToUpdate == null) {
			throw new GenesisNotValideDataException("Data not valided - NULL");
		}

		// Main Addresse !! Not Da
		if (CompanyToUpdate.getMainAddressCompany() == null) {
			throw new GenesisNotValideDataException("Data not valided - MainAddress :: NULL");

		} else {
			// Validate Addrees
			if (1 == 1) {

			}
		}

		// HeadOffpCompanyice Addresse
		if (CompanyToUpdate.getMainAddressHeadOffice() == null) {
			throw new GenesisNotValideDataException("Data not valided - AddressHeadOffice :: NULL");

		} else {
			// Validate Addrees
			if (1 == 1) {

			}
		}

		// Modification
		Company CompanyUpdate = CompanyService.findCompanyById(id);

		if (CompanyUpdate == null) {
			throw new GenesisNotValideDataException("Data not founded - NULL");
		}

		// Test @Version
		{

		}

		// Adapt - Name
		if (CompanyToUpdate.getCompanyName() != null && CompanyToUpdate.getCompanyName().length() > 0) {
			CompanyUpdate.setCompanyName(CompanyToUpdate.getCompanyName());
		}

		// Adapt - TVA
		if (CompanyToUpdate.getCompanyTva() != null && CompanyToUpdate.getCompanyTva().length() > 0) {
			CompanyUpdate.setCompanyTva(CompanyToUpdate.getCompanyTva());
		}

		// Adapt - MainAddressCompany()
		if (CompanyToUpdate.getMainAddressCompany() != null) {

			// --> Name Modification
			if (CompanyUpdate.getMainAddressCompany().getAdresseId().longValue() == CompanyToUpdate
					.getMainAddressCompany().getAdresseId().longValue()) {

				// NEED TEST VALUES --> FUTUR

				CompanyUpdate.getMainAddressCompany()
						.setAddressCity(CompanyToUpdate.getMainAddressCompany().getAddressCity());
				CompanyUpdate.getMainAddressCompany()
						.setAddressStreet(CompanyToUpdate.getMainAddressCompany().getAddressStreet());

			}

			// --> ID Modification (Only ID --> No Name)
			if (CompanyUpdate.getMainAddressCompany().getAdresseId().longValue() != CompanyToUpdate
					.getMainAddressCompany().getAdresseId().longValue()) {

				// -->
				Long NewId = CompanyToUpdate.getMainAddressCompany().getAdresseId().longValue();

				// Find Data
				Address AddressCompany = AddressService.findAddressById(NewId);

				// Adapt
				CompanyUpdate.setAddressMain(AddressCompany);

			}

		}

		// Adapt - HeadOffpCompanyice
		if (CompanyToUpdate.getMainAddressHeadOffice() != null) {

			// --> Name Modification
			if (CompanyUpdate.getMainAddressHeadOffice().getAdresseId().longValue() == CompanyToUpdate
					.getMainAddressHeadOffice().getAdresseId().longValue()) {

				// NEED TEST VALUES --> FUTUR

				CompanyUpdate.getMainAddressHeadOffice()
						.setAddressCity(CompanyToUpdate.getMainAddressHeadOffice().getAddressCity());
				CompanyUpdate.getMainAddressHeadOffice()
						.setAddressStreet(CompanyToUpdate.getMainAddressHeadOffice().getAddressStreet());

			}

			// --> ID Modification (Only ID --> No Name)
			if (CompanyUpdate.getMainAddressHeadOffice().getAdresseId().longValue() != CompanyToUpdate
					.getMainAddressHeadOffice().getAdresseId().longValue()) {

				// -->
				Long NewId = CompanyToUpdate.getMainAddressHeadOffice().getAdresseId().longValue();

				// Find Data
				Address AddressHeadOffice = AddressService.findAddressById(NewId);

				// Adapt --> ()
				CompanyUpdate.setAddressHeadOffice(AddressHeadOffice);
			}

		}

		// Search
		// Contact OldValue = contactService.findContactById(id);

		// Update
		return CompanyService.saveCompany(CompanyUpdate);

	}

}
