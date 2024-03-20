package org.example.security.seeder;

import org.example.repository.FutbolTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class FutbolTeamDataSeed implements CommandLineRunner {

    @Autowired
    private FutbolTeamRepository futbolTeamRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        loadFutbolTeam();
    }

    private void loadFutbolTeam() {
        if(futbolTeamRepository.count() == 0){
            createFutbolTeams();
        }
    }

    private void createFutbolTeams() {
        try {
            // Leer el script SQL desde el archivo
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("futbol_teams.sql");
            String script = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));
            // Ejecutar el script SQL
            jdbcTemplate.execute(script);
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir
            e.printStackTrace();
        }
    }
}