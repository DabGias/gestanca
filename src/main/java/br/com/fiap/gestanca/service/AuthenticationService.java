package br.com.fiap.gestanca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.fiap.gestanca.repositories.UsuarioRepository;

public class AuthenticationService implements UserDetailsService {
    
    @Autowired
    UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    }
}
