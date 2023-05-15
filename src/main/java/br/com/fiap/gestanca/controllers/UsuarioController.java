package br.com.fiap.gestanca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiap.gestanca.models.Credencial;
import br.com.fiap.gestanca.models.Usuario;
import br.com.fiap.gestanca.repositories.UsuarioRepository;
import br.com.fiap.gestanca.service.TokenService;
import jakarta.validation.Valid;

public class UsuarioController {
    @Autowired
    UsuarioRepository repo;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    @PostMapping("/api/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario u) {
        u.setSenha(encoder.encode(u.getSenha()));
        repo.save(u);

        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial) {
        manager.authenticate(credencial.toAuthentication());
        var token = tokenService.generateToken(credencial);

        return ResponseEntity.ok(token);
    }
}
