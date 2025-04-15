package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginForm {

	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "有効なメールアドレスを入力してください")
	private String email;

	@NotBlank(message = "パスワードを入力してください")
	private String password;

	// ゲッター・セッター
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
