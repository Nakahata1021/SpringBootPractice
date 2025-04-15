package com.example.demo.service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {

	void saveContact(ContactForm contactForm);

	// お問い合わせ情報を全件取得
	Iterable<Contact> getAllContacts();
}
