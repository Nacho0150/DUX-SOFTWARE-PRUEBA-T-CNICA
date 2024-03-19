package org.example.service;

import org.example.DTO.FutbolTeamDTO;
import org.example.exception.Exceptions;

import java.text.ParseException;
import java.util.List;

public interface FutbolTeamService {

    List<FutbolTeamDTO> getAllFutbolTeams();

    FutbolTeamDTO getFutbolTeamById(Long id) throws ParseException, Exceptions;

    List<FutbolTeamDTO> getFutbolTeamsByName(String nombre) throws ParseException;

    FutbolTeamDTO save(FutbolTeamDTO futbolTeam) throws ParseException;

    FutbolTeamDTO update(Long id, FutbolTeamDTO futbolTeam) throws ParseException, Exceptions;

    void delete(Long id) throws ParseException;
}