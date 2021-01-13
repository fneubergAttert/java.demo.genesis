/**
 * 
 */
package com.genesis.case01.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.genesis.case01.domain.Contact;
import com.genesis.case01.respositories.ContactRepository;

/**
 * @author fred_
 *
 */
@Service
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactRepository;

	public ContactServiceImpl(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Override
	public Contact findContactById(Long id) {

		Contact exContact = contactRepository.findById(id).get();

		return exContact;

	}

	@Override
	public List<Contact> findAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public Contact saveContact(Contact contact) {
		return contactRepository.save(contact);
	}

	
	@Override
	public void deleteContactById(Long id) {
		contactRepository.deleteById(id);
	}

}
