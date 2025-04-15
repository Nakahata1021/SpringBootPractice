package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;

@Service
public class AdminContactService {

	private final ContactRepository contactRepository;

	@Autowired
	public AdminContactService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	public List<Contact> getAllContacts() {
		return contactRepository.findAll();  // お問い合わせ情報を取得
	}
}