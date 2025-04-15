package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;

@Controller
@RequestMapping("/admin/contacts")
public class AdminContactController {

	@Autowired
	private ContactService contactService;

	@Autowired
	private ContactRepository contactRepository;

	@GetMapping("")
	public String showContacts(Model model) {
		Iterable<Contact> contacts = contactService.getAllContacts();
		model.addAttribute("contacts", contacts);
		return "admin/contacts/list";
	}

	@GetMapping("/{id}")
	public String getContactDetail(@PathVariable Long id, Model model) {
		Contact contact = contactRepository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
		model.addAttribute("contact", contact);
		return "admin/contact-detail";
	}

	// 編集フォームを表示するメソッド
	@GetMapping("/{id}/edit")
	public String editContactForm(@PathVariable Long id, Model model) {
		Contact contact = contactRepository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

		model.addAttribute("contact", contact);
		return "admin/contacts/edit"; // 編集用のHTML
	}

	// 編集後のPOST処理
	@PostMapping("/{id}/edit")
	public String updateContact(@PathVariable Long id, @ModelAttribute Contact formContact) {
		// IDで対象のContactを取得
		Contact contact = contactRepository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

		// フォームから送られたデータでフィールドを更新
		contact.setLastName(formContact.getLastName());
		contact.setFirstName(formContact.getFirstName());
		contact.setEmail(formContact.getEmail());
		contact.setPhone(formContact.getPhone());
		contact.setZipCode(formContact.getZipCode());
		contact.setAddress(formContact.getAddress());
		contact.setBuildingName(formContact.getBuildingName());
		contact.setContactType(formContact.getContactType());
		contact.setBody(formContact.getBody());

		// 更新日時をセット
		contact.setUpdatedAt(java.time.LocalDateTime.now());

		// 更新したContactを保存
		contactRepository.save(contact);

		// 更新後は一覧画面にリダイレクト
		return "redirect:/admin/contacts";
	}
}