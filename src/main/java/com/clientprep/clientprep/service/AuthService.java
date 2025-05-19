package com.clientprep.clientprep.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clientprep.clientprep.entity.User;
import com.clientprep.clientprep.repository.UserRepository;
import com.clientprep.clientprep.security.JwtUtil;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(User user) {
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    public ResponseEntity<?> login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        System.out.println("Inside login");
        // 2. Load user details for JWT
        UserDetails userDetails = userRepository.findByEmail(email)
            .map(u -> org.springframework.security.core.userdetails.User
                    .withUsername(u.getEmail()) // FIXED LINE
                    .password(u.getPassword())
                    .roles(u.getRole().name())
                    .build())
            .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Generate JWT token
        String token = jwtUtil.generateToken(userDetails.getUsername());

        // 4. Return response
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
