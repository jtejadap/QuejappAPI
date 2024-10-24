package com.example.quejapp;

import com.example.quejapp.model.Queja;
import com.example.quejapp.model.Usuario;
import com.example.quejapp.model.repositories.QuejaRepository;
import com.example.quejapp.model.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Configuration
class LoadDatabase {
    private final PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    LoadDatabase(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository repositorio, QuejaRepository quejas) {

        return args -> {
            /*
            //datos semilla para llenar la BD en caso de estar recien creada
            Usuario usuario1 = new Usuario("Carlos", "Peres", "CPerez", "cprez@demo.com", new Date(), 1,"USER");
            usuario1.setPassword(passwordEncoder.encode("123456"));
            Usuario usuario2 = new Usuario("Andrea", "Amaya", "AMaya", "AAmaya@demo.com", new Date(), 2,"ADMINISTRATOR");
            usuario2.setPassword(passwordEncoder.encode("123456"));
            log.info("Cargando " + repositorio.save(usuario1));
            log.info("Cargando " + repositorio.save(usuario2));
            log.info("Cargando " + quejas.save(new Queja(
                    new Date(),
                    "Se me quedo un bolso en la estación centro pero me acerco a la portería para recuperarlo y me dicen que no es problema de ellos que podría acércame a la sede de gerencia ubicada en el portal para mas detalles",
                    1,
                    "Estacion Centro",
                    1L))
            );
            log.info("Cargando " + quejas.save(new Queja(new Date(), "Reporte de Falla en el servicio", 2,"Estacion Portal", 1L)));
            log.info("Cargando " + quejas.save(new Queja(
                    new Date(),
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam id posuere massa. In volutpat vulputate metus, et bibendum ex. Donec ac nisi consectetur, volutpat mauris fringilla, hnterdum neque. Maecenas ac scelerisque metus.",
                    3,
                    "Av. El Consulado" ,
                    1L))
            );
            */
        };
    }
}
