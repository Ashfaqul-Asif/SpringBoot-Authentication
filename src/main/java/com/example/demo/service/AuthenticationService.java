package com.example.demo.service;

import com.example.demo.controller.AuthenticateRequest;
import com.example.demo.controller.AuthenticationResponse;
import com.example.demo.controller.RegisterRequest;
import com.example.demo.model.Admin;
import com.example.demo.model.Role;
import com.example.demo.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AdminRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var admin = Admin.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN_USER)
                .build();
        repository.save(admin);
        var jwtToken = jwtService.generateToken(admin);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public  AuthenticationResponse authenticate (AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var admin = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(admin);

        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
