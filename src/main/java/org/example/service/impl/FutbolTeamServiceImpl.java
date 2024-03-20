package org.example.service.impl;

import org.example.DTO.FutbolTeamDTO;
import org.example.entity.FutbolTeamEntity;
import org.example.exception.Exceptions;
import org.example.mapper.FutbolTeamMapper;
import org.example.repository.FutbolTeamRepository;
import org.example.service.FutbolTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FutbolTeamServiceImpl implements FutbolTeamService {

    @Autowired
    private FutbolTeamMapper futbolTeamMapper;

    @Autowired
    private FutbolTeamRepository futbolTeamRepository;

    private static final String ERROR_ID = "Id del equipo no válido";

    @Override
    public List<FutbolTeamDTO> getAllFutbolTeams() throws Exceptions {
        List<FutbolTeamEntity> entity = futbolTeamRepository.findAll();
        if (entity.isEmpty()) {
            throw new Exceptions("No se encontraron equipos");
        }
        return futbolTeamMapper.futbolTeamEntityListToDTOList(entity);
    }

    @Override
    public FutbolTeamDTO getFutbolTeamById(Long id) throws Exceptions {
        Optional<FutbolTeamEntity> futbolTeam = futbolTeamRepository.findById(id);
        if (!futbolTeam.isPresent()) {
            throw new Exceptions(ERROR_ID);
        }
        return futbolTeamMapper.futbolTeamEntityToDTO(futbolTeam.get());
    }

    @Override
    public List<FutbolTeamDTO> getFutbolTeamsByName(String nombre) throws Exceptions {
        List<FutbolTeamEntity> entity = futbolTeamRepository.findAllByName(nombre);
        if (entity.isEmpty()) {
            throw new Exceptions("No se encontró/encontraron equipo/s con ese nombre");
        }
        return futbolTeamMapper.futbolTeamEntityListToDTOList(entity);
    }

    @Override
    public FutbolTeamDTO save(FutbolTeamDTO futbolTeamDTO) throws Exceptions {
        FutbolTeamEntity entitySaved = null;
        FutbolTeamEntity entity = futbolTeamMapper.futbolTeamDTOToEntity(futbolTeamDTO);
        if(futbolTeamRepository.findByName(entity.getNombre()) == null){
            entitySaved = futbolTeamRepository.save(entity);
        } else {
            throw new Exceptions("El equipo ya existe");
        }
        return futbolTeamMapper.futbolTeamEntityToDTO(entitySaved);
    }

    @Override
    public FutbolTeamDTO update(Long id, FutbolTeamDTO futbolTeamDTO) throws Exceptions {
        Optional<FutbolTeamEntity> entity = futbolTeamRepository.findById(id);
        if (!entity.isPresent()) {
            throw new Exceptions(ERROR_ID);
        }
        futbolTeamMapper.futbolTeamEntityUpdates(entity.get(), futbolTeamDTO);
        FutbolTeamEntity entitySaved = futbolTeamRepository.save(entity.get());
        return futbolTeamMapper.futbolTeamEntityToDTO(entitySaved);
    }

    @Override
    public void delete(Long id) throws Exceptions {
        Optional<FutbolTeamEntity> entity = futbolTeamRepository.findById(id);
        if (entity.isPresent()) {
            futbolTeamRepository.deleteById(id);
        } else {
            throw new Exceptions(ERROR_ID);
        }
    }
}