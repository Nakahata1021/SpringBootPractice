package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.LoginForm;
import com.example.demo.service.AuthenticationService;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

	private final AuthenticationService authenticationService;

	@Autowired
	public AdminLoginController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	// 管理者ログイン画面を表示
	@GetMapping("/signin")
	public String showLoginForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "signin";
	}

	// ログイン処理
	@PostMapping("/signin")
	public String login(@ModelAttribute LoginForm loginForm, Model model) {
		try {
			// 認証処理を行い、成功すればダッシュボードに遷移
			if (authenticationService.authenticate(loginForm.getEmail(), loginForm.getPassword())) {
				return "redirect:/admin/contacts"; // ログイン成功後のリダイレクト先
			} else {
				model.addAttribute("error", "Invalid credentials"); // 認証失敗メッセージ
				return "signin"; // ログイン失敗時、再度サインイン画面を表示
			}
		} catch (BadCredentialsException e) {
			model.addAttribute("error", "Invalid credentials");  // エラー表示
			return "signin"; // ログイン失敗時、再度サインイン画面を表示
		}
	}
}