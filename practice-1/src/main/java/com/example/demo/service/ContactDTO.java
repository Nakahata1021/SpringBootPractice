package com.example.demo.service;

import java.time.LocalDateTime;

import com.example.demo.entity.Contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

	private Long id;
	private String lastName;
	private String firstName;
	private String contactType;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// Contactエンティティ → DTOへの変換用コンストラクタ
	public ContactDTO(Contact contact) {
		this.id = contact.getId();
		this.lastName = contact.getLastName();
		this.firstName = contact.getFirstName();
		this.contactType = contact.getContactType();
		this.createdAt = contact.getCreatedAt();
		this.updatedAt = contact.getCreatedAt();
	}
}