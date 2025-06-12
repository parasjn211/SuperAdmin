package com.superadmin.controller;


import com.superadmin.dto.JwtRequest;
import com.superadmin.entity.SuperAdmin;
import com.superadmin.jwt.JwtResponse;
import com.superadmin.jwt.JwtService;
import com.superadmin.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest loginRequest) {
        Optional<SuperAdmin> adminOpt = superAdminService.authenticate(
                loginRequest.getEmail(), loginRequest.getPassword());

        if (adminOpt.isPresent()) {
            SuperAdmin admin = adminOpt.get();
            String token = jwtService.generateToken(admin.getEmail());

            JwtResponse response = new JwtResponse(token, "Login Successful");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity
                    .status(401)
                    .body(new JwtResponse(null, "Invalid Credentials"));
        }
    }
}
