package org.example.controller;

import org.example.DTO.FutbolTeamDTO;
import org.example.exception.Exceptions;
import org.example.service.FutbolTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("equipos")
public class TeamController {

    @Autowired
    private FutbolTeamService futbolTeamService;

    @GetMapping
    public ResponseEntity<List<FutbolTeamDTO>> getAllFutbolTeams(){ //PARA OBTENER TODOS LOS EQUIPOS DE FUTBOL
        List<FutbolTeamDTO> futbolTeams = futbolTeamService.getAllFutbolTeams();
        return ResponseEntity.status(HttpStatus.OK).body(futbolTeams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FutbolTeamDTO> getFutbolTeamById(@PathVariable Long id) throws ParseException, Exceptions { //PARA OBTENER A UN EQUIPO POR ID
        FutbolTeamDTO futbolTeam = futbolTeamService.getFutbolTeamById(id);
        return ResponseEntity.status(HttpStatus.OK).body(futbolTeam);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<FutbolTeamDTO>> getFutbolTeamsByName(@RequestParam(required = false) String nombre) throws ParseException { //PARA OBTENER A UN EQUIPO POR NOMBRE
        List<FutbolTeamDTO> futbolTeams = futbolTeamService.getFutbolTeamsByName(nombre);
        return ResponseEntity.status(HttpStatus.OK).body(futbolTeams);
    }

    @PostMapping
    public ResponseEntity<FutbolTeamDTO> save(@RequestBody FutbolTeamDTO futbolTeam) throws ParseException { //PARA GUARDAR LOS EQUIPOS
        FutbolTeamDTO futbolTeamSave = futbolTeamService.save(futbolTeam);
        return ResponseEntity.status(HttpStatus.CREATED).body(futbolTeamSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FutbolTeamDTO> update(@PathVariable Long id, @RequestBody FutbolTeamDTO futbolTeam) throws ParseException, Exceptions { //PARA ACTUALIZAR LOS EQUIPOS
        FutbolTeamDTO futbolTeamUpdate = futbolTeamService.update(id, futbolTeam);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(futbolTeamUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ParseException { //PARA ELIMINAR LOS EQUIPOS
        futbolTeamService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}