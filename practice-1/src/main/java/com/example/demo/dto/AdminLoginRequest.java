package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AdminLoginRequest {
	private String email;
	private String password;

	public AdminLoginRequest() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

}
