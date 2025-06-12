package com.superadmin.controller;

import com.superadmin.dto.SuperAdminLoginRequest;
import com.superadmin.entity.SuperAdmin;
import com.superadmin.service.JwtService;
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
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody SuperAdminLoginRequest loginRequest) {
        return superAdminService.authenticate(loginRequest.getEmail(), loginRequest.getPassword())
                .map(admin -> {
                    String token = jwtService.generateToken(admin.getEmail());
                    return ResponseEntity.ok(Map.of(
                            "message", "Login Successful",
                            "token", token,
                            "name", admin.getName()
                    ));
                })
                .orElse(ResponseEntity.status(401).body(
                        Map.of("error", "Invalid Credentials")
                ));
    }
    @GetMapping("/superadmin/dashboard")
    public ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("Welcome to the Super Admin Dashboard!");
    }

    @PostMapping("/register-superadmin")
    public ResponseEntity<SuperAdmin> register(@RequestBody SuperAdmin admin) {
        return ResponseEntity.ok(superAdminService.saveSuperAdmin(admin));
    }


}
