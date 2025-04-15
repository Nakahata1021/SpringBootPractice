package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
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

	    ContactForm form = new ContactForm();
	    form.setLastName(contact.getLastName());
	    form.setFirstName(contact.getFirstName());
	    form.setEmail(contact.getEmail());
	    form.setPhone(contact.getPhone());
	    form.setZipCode(contact.getZipCode());
	    form.setAddress(contact.getAddress());
	    form.setBuildingName(contact.getBuildingName());
	    form.setContactType(contact.getContactType());
	    form.setBody(contact.getBody());

	    model.addAttribute("contactForm", form);
	    model.addAttribute("contactId", id); // フォーム用URLに使うため

	    return "admin/contacts/edit";
	}

	// 編集反映処理
	@PostMapping("/{id}/edit")
	public String updateContact(
	        @PathVariable Long id,
	        @ModelAttribute("contactForm") @Valid ContactForm form,
	        BindingResult bindingResult,
	        Model model) {

	    if (bindingResult.hasErrors()) {
	        model.addAttribute("contactId", id); // URL用に必要
	        return "admin/contacts/edit"; // バリデーションエラー時は再表示
	    }

	    Contact contact = contactRepository.findById(id)
	        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

	    // 更新
	    contact.setLastName(form.getLastName());
	    contact.setFirstName(form.getFirstName());
	    contact.setEmail(form.getEmail());
	    contact.setPhone(form.getPhone());
	    contact.setZipCode(form.getZipCode());
	    contact.setAddress(form.getAddress());
	    contact.setBuildingName(form.getBuildingName());
	    contact.setContactType(form.getContactType());
	    contact.setBody(form.getBody());
	    contact.setUpdatedAt(java.time.LocalDateTime.now());

	    contactRepository.save(contact);

	    return "redirect:/admin/contacts/" + id; // 詳細画面にリダイレクト
	}
	
	@PostMapping("/{id}/delete")
    public String deleteContact(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        contactRepository.delete(contact);

        return "redirect:/admin/contacts";
    }
	
}