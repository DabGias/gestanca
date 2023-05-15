package br.com.fiap.gestanca.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.gestanca.models.Conta;
import br.com.fiap.gestanca.models.Despesa;
import br.com.fiap.gestanca.models.Usuario;
import br.com.fiap.gestanca.repositories.ContaRepository;
import br.com.fiap.gestanca.repositories.DespesaRepository;
import br.com.fiap.gestanca.repositories.UsuarioRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {
    
    @Autowired
    ContaRepository repoConta;

    @Autowired
    DespesaRepository repoDespesa;

    @Autowired
    UsuarioRepository repoUsuario;

    @Autowired
    PasswordEncoder encoder;
    
    @Override
    public void run(String... args) throws Exception {
        Conta c1 = new Conta(1L, "Itaú", new BigDecimal(100), "money", null);
        Conta c2 = new Conta(2L, "Nubank", new BigDecimal(200), "money", null);
        Conta c3 = new Conta(3L, "Banco", new BigDecimal(300), "money", null);
        Conta c4 = new Conta(4L, "Bancovisk", new BigDecimal(400), "money", null);
        Conta c5 = new Conta(5L, "Cadeira", new BigDecimal(500), "money", null);
        Conta c6 = new Conta(6L, "Manjericão", new BigDecimal(600), "money", null);
        Conta c7 = new Conta(7L, "Bancowíc", new BigDecimal(700), "money", null);
        Conta c8 = new Conta(8L, "Banco Inter", new BigDecimal(800), "money", null);

        repoConta.saveAll(List.of(
            c1,
            c2,
            c3,
            c4,
            c5,
            c6,
            c7,
            c8
        ));

        repoDespesa.saveAll(List.of(
            Despesa.builder().valor(new BigDecimal(100)).data(LocalDate.now()).descricao("Aluguel").conta(c1).build(),
            Despesa.builder().valor(new BigDecimal(200)).data(LocalDate.now()).descricao("Aluguel").conta(c2).build(),
            Despesa.builder().valor(new BigDecimal(300)).data(LocalDate.now()).descricao("Aluguel").conta(c3).build(),
            Despesa.builder().valor(new BigDecimal(400)).data(LocalDate.now()).descricao("Aluguel").conta(c4).build(),
            Despesa.builder().valor(new BigDecimal(500)).data(LocalDate.now()).descricao("Aluguel").conta(c5).build(),
            Despesa.builder().valor(new BigDecimal(600)).data(LocalDate.now()).descricao("Aluguel").conta(c6).build(),
            Despesa.builder().valor(new BigDecimal(700)).data(LocalDate.now()).descricao("Aluguel").conta(c7).build(),
            Despesa.builder().valor(new BigDecimal(800)).data(LocalDate.now()).descricao("Aluguel").conta(c8).build()
        ));

        repoUsuario.save(Usuario.builder().nome("Gabriel Dias").email("gabrieldias@email.com").senha(encoder.encode("1234567890")).build());
    }
}
