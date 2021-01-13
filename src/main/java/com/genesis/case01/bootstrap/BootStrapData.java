package com.genesis.case01.bootstrap;

import org.springframework.boot.CommandLineRunner;
import com.genesis.case01.domain.Address;
import org.springframework.stereotype.Component;

import com.genesis.case01.domain.Company;
import com.genesis.case01.domain.Contact;
import com.genesis.case01.respositories.AddressRepository;
import com.genesis.case01.respositories.CompanyRepository;
import com.genesis.case01.respositories.ContactRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	private final ContactRepository contactRepository;
	private final CompanyRepository companyRepository;
	private final AddressRepository addressRepository;

	public BootStrapData(ContactRepository contactRepository, CompanyRepository companyRepository,
			AddressRepository addressRepository) {
		this.contactRepository = contactRepository;
		this.companyRepository = companyRepository;
		this.addressRepository = addressRepository;

	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Start Database  - Initialisation");

		Contact C2 = new Contact();
		C2.setContactFirstName("Emilie");
		C2.setContactLasteName("Oxford");
		C2.setContactAdress("Bruxelles");
		C2.setContactFreelance(true);
		C2.setContactTVA("BE007-007-007-123");
		contactRepository.save(C2);

		System.out.println("Contacts Saved : " + contactRepository.count());

		Company CP1 = new Company();
		CP1.setCompanyName("Lotus");
		CP1.setCompanyTva("BE-78979879-LT");
		companyRepository.save(CP1);

		Company CP2 = new Company();
		CP2.setCompanyName("Ford");
		CP2.setCompanyTva("BE-78899879-FF");
		companyRepository.save(CP2);

		// Generate Adresse
		Address AddressSolo0 = new Address("Place sainte catherine","Bruxelles");
		Address AddressSolo1 = new Address("Main Street", "London");
		Address AddressSolo2 = new Address("Blue Street", "Lisbon");
		Address AddressSolo3 = new Address("Green Street","Venise");

		AddressSolo0 = addressRepository.save(AddressSolo0);
		AddressSolo1 = addressRepository.save(AddressSolo1);
		AddressSolo2 = addressRepository.save(AddressSolo2);
		AddressSolo3 = addressRepository.save(AddressSolo3);

		// Add Address to Company 1
		CP1.addAddress(AddressSolo1);
		CP1.addAddressHeadOffice(AddressSolo0);
		CP1.addAddressMain(AddressSolo2);
		companyRepository.save(CP1);

		// Add address to Company 2
		CP2.addAddressHeadOffice(AddressSolo0);
		CP2.addAddressMain(AddressSolo2);
		companyRepository.save(CP2);

		// Link Contact Company
		Contact C1 = new Contact();
		C1.setContactFirstName("Durant");
		C1.setContactLasteName("Paul");
		C1.setContactAdress("Verviers");
		C1.setContactFreelance(false);
		contactRepository.save(C1);

		C1.addCompany(CP1);

		contactRepository.save(C1);

		System.out.println("End Database  - Initialisation");

	}

}
