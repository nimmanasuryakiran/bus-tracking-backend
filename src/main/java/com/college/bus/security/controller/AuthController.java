
package com.college.bus.security.controller;


import com.college.bus.security.JwtUtil;
import com.college.bus.security.dto.AuthRequest;
import com.college.bus.security.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
    	System.out.println("Login attempt for: " + authRequest.getUsername() + " with role: " + authRequest.getRole());
        
        String token = authService.authenticateUser(authRequest);
        return ResponseEntity.ok().body(token);
    }
}



