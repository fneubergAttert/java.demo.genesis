package com.genesis.case01.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.case01.domain.Company;
import com.genesis.case01.domain.Contact;
import com.genesis.case01.exceptions.GenesisNotValideDataException;
import com.genesis.case01.services.CompanyService;
import com.genesis.case01.services.ContactService;

@RestController
@RequestMapping(ContactController.BASE_URL)
public class ContactController {

	public static final String BASE_URL = "/api/v1/Contacts";

	private final ContactService contactService;
	private final CompanyService companyService;

	public ContactController(ContactService contactService, CompanyService companyService) {
		super();
		this.contactService = contactService;
		this.companyService = companyService;
	}

	// GET /objects/ Gets all Objects
	@GetMapping
	public List<Contact> findAllContacts() {
		return contactService.findAllContacts();
	}

	// GET /object/ID Gets an Object with specified ID
	@GetMapping("/{id}")
	public Contact findContactById(@PathVariable Long id) {
		return contactService.findContactById(id);
	}

	// POST /objects Adds a new Object
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contact saveContact(@RequestBody Contact contact) {

		// Validation
		if (contact == null) {
			throw new GenesisNotValideDataException("Data not valided - NULL");
		}

		if ((contact.getContactFirstName() == null) || (contact.getContactFirstName().trim().length() < 2)) {
			throw new GenesisNotValideDataException("Data not valided - FirstName - NULL");
		}

		if ((contact.getContactLasteName() == null) || (contact.getContactLasteName().trim().length() < 2)) {
			throw new GenesisNotValideDataException("Data not valided - LastName - NULL");
		}

		if (contact.getContactFreelance() == null) {
			throw new GenesisNotValideDataException("Data not valided - Freelance - NULL");
		}
		if (contact.getContactFreelance().booleanValue() == true) {
			// Must have a TVA
			if ((contact.getContactTVA() == null) || (contact.getContactTVA().trim().length() < 6)) {
				throw new GenesisNotValideDataException("Data not valided - Freelance - TVA Not Valide");
			}
		}

		return contactService.saveContact(contact);
	}

	// PUT /object/ID Adds an Object with specified ID, Updates an Object
	@PutMapping("/{id}")
	public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact) {

		// Validation
		if (contact == null) {
			throw new GenesisNotValideDataException("Data not valided - NULL");
		}

		// Search
		Contact OldValue = contactService.findContactById(id);

		// Update
		return contactService.saveContact(contact);

	}

	// DELETE /object/ID Deletes the object with specified ID
	// Status: 200 OK
	@DeleteMapping("/{id}")
	public void deleteContact(@PathVariable Long id) {
		contactService.deleteContactById(id);

	}

	/**
	 * 
	 * @param id
	 * @param companyid
	 * @return
	 */
	@PutMapping("/{id}/company/{companyid}")
	public Contact updateContact(@PathVariable Long id, @PathVariable Long companyid) {

		// Validation
		// if (contact == null) {
		// throw new GenesisNotValideDataException("Data not valided - NULL");
		// }

		// Search
		Contact varContact = contactService.findContactById(id);

		// Check if not working for this company

		// Company filtree
		List<Company> compaynyFiltered = varContact.getCompanyList() //
				.stream() //
				.filter(c -> c.getCompanyId().longValue()== companyid.longValue()) //
				.collect(Collectors.toList()//
				);

		// Are Working
		if (compaynyFiltered.size()> 0) {
			return varContact;
		}
		
		
		// Search
		Company varCompany = companyService.findCompanyById(companyid);

		// On ajoute
		varContact.addCompany(varCompany);

		// Update
		return contactService.saveContact(varContact);

	}

	// DELETE /object/ID Deletes the object with specified ID
	// Status: 200 OK
	@DeleteMapping("/{id}/company/{companyid}")
	public Contact deleteContact(@PathVariable Long id, @PathVariable Long companyid) {

		// Search
		Contact varContact = contactService.findContactById(id);

		// Search
		Company varCompany = companyService.findCompanyById(companyid);

		// On ajoute
		varContact.removeCompany(varCompany);

		// Update
		return contactService.saveContact(varContact);

	}

}
