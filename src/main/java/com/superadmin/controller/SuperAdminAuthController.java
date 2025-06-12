package com.superadmin.controller;

import com.superadmin.dto.SuperAdminLoginRequest;
import com.superadmin.entity.SuperAdmin;
import com.superadmin.jwt.JwtResponse;
import com.superadmin.jwt.JwtService;
import com.superadmin.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class SuperAdminAuthController {
    @Autowired
    private SuperAdminService superAdminService;
    @Autowired
    private JwtService jwtService;
    @GetMapping("/superadmin/dashboard")
    public ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("Welcome to the Super Admin Dashboard!");
    }

    @PostMapping("/register-superadmin")
    public ResponseEntity<SuperAdmin> register(@RequestBody SuperAdmin admin) {
        return ResponseEntity.ok(superAdminService.saveSuperAdmin(admin));
    }


}
