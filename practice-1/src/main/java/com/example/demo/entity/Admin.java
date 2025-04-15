package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "admins")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String lastName;  // 姓

	@Column(nullable = false)
	private String firstName; // 名

	@Column(nullable = false, unique = true)
	private String email;     // メールアドレス

	@Column(nullable = false)
	private String password;  // パスワード（暗号化）
}
