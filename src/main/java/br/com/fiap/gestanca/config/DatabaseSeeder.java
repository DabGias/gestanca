package br.com.fiap.gestanca.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.gestanca.models.Conta;
import br.com.fiap.gestanca.repositories.ContaRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {
    @Autowired
    ContaRepository repoConta;
    
    @Override
    public void run(String... args) throws Exception {
        repoConta.saveAll(List.of(
            new Conta("Itaú", new BigDecimal(100), "money", null),
            new Conta("Nubank", new BigDecimal(200), "money", null),
            new Conta("Banco", new BigDecimal(300), "money", null),
            new Conta("Bancovisk", new BigDecimal(400), "money", null),
            new Conta("Cadeira", new BigDecimal(500), "money", null),
            new Conta("Manjericão", new BigDecimal(600), "money", null),
            new Conta("Bancowíc", new BigDecimal(700), "money", null),
            new Conta("Banco Inter", new BigDecimal(800), "money", null)
        ));
    }
}
