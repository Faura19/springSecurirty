package org.vedruna.frogger.security.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vedruna.frogger.dto.ResponseDTO;
import org.vedruna.frogger.security.auth.dto.AuthResponseDTO;
import org.vedruna.frogger.security.auth.dto.LoginRequestDTO;
import org.vedruna.frogger.security.auth.dto.RegisterRequestDTO;
import org.vedruna.frogger.security.auth.service.AuthServiceI;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthServiceI authService;

    // 2 End point del login que lo hacemos con un post

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // 1 End point del register que lo hacemos con un post

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        authService.register(request);
        return ResponseEntity.ok(new ResponseDTO("User registered successfully"));
    }

}
