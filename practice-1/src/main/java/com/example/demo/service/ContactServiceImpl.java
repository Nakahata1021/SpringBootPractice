package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactRepository;

	public ContactServiceImpl(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Override
	public void saveContact(ContactForm contactForm) {
		Contact contact = new Contact();
		contact.setFirstName(contactForm.getFirstName());
		contact.setLastName(contactForm.getLastName());
		contact.setEmail(contactForm.getEmail());
		contact.setBody(contactForm.getBody());
		contactRepository.save(contact);
	}

	// `getAllContacts()` を実装する
	@Override
	public Iterable<Contact> getAllContacts() {
		return contactRepository.findAll();
	}
}

