package com.example.demo.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;

@Service
public class AuthenticationService {

    private final AdminService adminService;

    public AuthenticationService(AdminService adminService) {
        this.adminService = adminService;
    }

    public boolean authenticate(String email, String password) {
        Admin admin = adminService.findByEmail(email); // emailでAdminを取得
        
        if (adminService.checkPassword(admin, password)) {
            return true; // パスワードが一致すれば認証成功
        } else {
            throw new BadCredentialsException("Invalid credentials"); // パスワードが一致しなければエラー
        }
    }
}