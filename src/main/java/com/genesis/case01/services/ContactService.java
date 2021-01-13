package com.genesis.case01.services;

import java.util.List;

import com.genesis.case01.domain.Contact;

public interface ContactService {

	
	public Contact findContactById(Long id);
	
	
	public List<Contact> findAllContacts();


	public Contact saveContact(Contact contact);


	public void deleteContactById(Long id);
	
}
