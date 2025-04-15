package com.example.demo.controller;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	private final AdminService adminService;
	private final PasswordEncoder passwordEncoder;

	public AdminController(AdminService adminService, PasswordEncoder passwordEncoder) {
		this.adminService = adminService;
		this.passwordEncoder = passwordEncoder;
	}

	// 管理者登録画面を表示
	@GetMapping("/signup")
	public String showSignupForm(Model model) {
		model.addAttribute("adminForm", new AdminForm());
		return "signup";
	}

	// 管理者登録処理
	@PostMapping("/signup")
	public String registerAdmin(@Valid AdminForm adminForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup"; // 入力エラーがある場合、登録画面に戻る
		}

		adminService.registerAdmin(adminForm);

		return "redirect:/admin/signin";
	}

	
}
