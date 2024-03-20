package org.example.service;

import org.example.DTO.FutbolTeamDTO;
import org.example.exception.Exceptions;

import java.util.List;

public interface FutbolTeamService {

    List<FutbolTeamDTO> getAllFutbolTeams() throws Exceptions;

    FutbolTeamDTO getFutbolTeamById(Long id) throws Exceptions;

    List<FutbolTeamDTO> getFutbolTeamsByName(String nombre) throws Exceptions;

    FutbolTeamDTO save(FutbolTeamDTO futbolTeam) throws Exceptions;

    FutbolTeamDTO update(Long id, FutbolTeamDTO futbolTeam) throws Exceptions;

    void delete(Long id) throws Exceptions;
}