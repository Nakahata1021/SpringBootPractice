package com.example.demo.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.AdminRepository; 

@Service
public class AdminService {
	private final AdminRepository adminRepository;
	private final PasswordEncoder passwordEncoder;

	public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
		this.adminRepository = adminRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void save(Admin admin) {
		adminRepository.save(admin);
	}

	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email)
				.orElseThrow(() -> new BadCredentialsException("Admin not found"));
	}

	// 管理者の新規登録
	public Admin registerAdmin(AdminForm form) {
		// 新しいAdminエンティティを作成
		Admin admin = new Admin();
		admin.setFirstName(form.getFirstName());
		admin.setLastName(form.getLastName());
		admin.setEmail(form.getEmail());
		
		// パスワードをハッシュ化してセット
		String hashedPassword = passwordEncoder.encode(form.getPassword());
		admin.setPassword(hashedPassword);

		// 新規登録したAdminをDBに保存
		return adminRepository.save(admin);
	}

	// パスワード照合
	public boolean checkPassword(Admin admin, String rawPassword) {
		// 入力されたパスワードと、データベースに保存されたハッシュパスワードを照合
		return passwordEncoder.matches(rawPassword, admin.getPassword());
	}
}
