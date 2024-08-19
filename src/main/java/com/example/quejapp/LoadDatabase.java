package com.example.quejapp;

import com.example.quejapp.model.Usuario;
import com.example.quejapp.model.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository repositorio) {

        return args -> {
            log.info("Cargando " + repositorio.save(new Usuario("Carlos", "Peres", "CPerez", "cprez@demo.com", new Date(), 1,"USER")));
            log.info("Cargando " + repositorio.save(new Usuario("Andrea", "Amaya", "AMaya", "AAmaya@demo.com", new Date(), 2,"USER")));
        };
    }
}
