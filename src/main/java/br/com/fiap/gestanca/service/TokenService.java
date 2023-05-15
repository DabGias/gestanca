package br.com.fiap.gestanca.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.gestanca.models.Credencial;
import br.com.fiap.gestanca.models.TokenJwt;

@Service
public class TokenService {
    
    public TokenJwt generateToken(Credencial credencial) {
        String token = JWT.create()
        .withSubject(credencial.email())
        .withIssuer("Gestança")
        .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
        .sign(Algorithm.HMAC256("secret"));
        
        return new TokenJwt(token);
    }

    public String validar(String token) {
        return JWT.require(Algorithm.HMAC256("secret"))
        .withIssuer("Gestança")
        .build()
        .verify(token)
        .getSubject();
    }
}
