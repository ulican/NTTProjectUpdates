package com.myapp.project.controller;

import com.myapp.project.domain.Owner;
import com.myapp.project.util.JwtUtil;
import com.myapp.project.service.OwnerService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

        import java.util.Map;

@RestController
public class AuthentificationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final OwnerService ownerService;

    public AuthentificationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, OwnerService ownerService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.ownerService = ownerService;
    }

    public JwtUtil getJwtUtil() {
        return jwtUtil;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            String token = jwtUtil.generateToken(email);
            return Map.of("token", token);
        } catch (AuthenticationException e) {
            return Map.of("message", "Invalid credentials");
        }
    }

    @PostMapping("/auth/register")
    public Map<String, String> register(@RequestBody Map<String, String> registerData) {
        String email = registerData.get("email");
        String name = registerData.get("name");
        String password = registerData.get("password");

        Owner newOwner = new Owner();
        newOwner.setEmail(email);
        newOwner.setName(name);
        newOwner.setPassword(password);

        Owner savedOwner = ownerService.registerOwner(newOwner);

        return Map.of("message", "User registered successfully", "id", String.valueOf(savedOwner.getId()));
    }

}

