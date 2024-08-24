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

import java.util.Date;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository repositorio, QuejaRepository quejas) {

        return args -> {
            log.info("Cargando " + repositorio.save(new Usuario("Carlos", "Peres", "CPerez", "cprez@demo.com", new Date(), 1,"USER")));
            log.info("Cargando " + repositorio.save(new Usuario("Andrea", "Amaya", "AMaya", "AAmaya@demo.com", new Date(), 2,"USER")));
            log.info("Cargando " + quejas.save(new Queja(new Date(), "Reporte de Servio al Cliente", 1,"Estacion Centro", 1L)));
            log.info("Cargando " + quejas.save(new Queja(new Date(), "Reporte de Falla en el servicio", 2,"Estacion Portal", 2L)));
            log.info("Cargando " + quejas.save(new Queja(new Date(), "Reporte de Retraso en el servicio", 3,"Av. El Consulado" ,1L)));
        };
    }
}
