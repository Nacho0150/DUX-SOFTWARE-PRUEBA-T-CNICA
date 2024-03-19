package org.example.service.impl;

import org.example.DTO.FutbolTeamDTO;
import org.example.entity.FutbolTeamEntity;
import org.example.exception.Exceptions;
import org.example.mapper.FutbolTeamMapper;
import org.example.repository.FutbolTeamRepository;
import org.example.service.FutbolTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class FutbolTeamServiceImpl implements FutbolTeamService{

    @Autowired
    private FutbolTeamMapper futbolTeamMapper;

    @Autowired
    private FutbolTeamRepository futbolTeamRepository;

    @Override
    public List<FutbolTeamDTO> getAllFutbolTeams() {
        List<FutbolTeamEntity> entity = futbolTeamRepository.findAll();
        return futbolTeamMapper.futbolTeamEntityListToDTOList(entity);
    }

    @Override
    public FutbolTeamDTO getFutbolTeamById(Long id) throws ParseException, Exceptions {
        Optional<FutbolTeamEntity> futbolTeam = futbolTeamRepository.findById(id);
        if (!futbolTeam.isPresent()){
            throw new Exceptions("Id del equipo no valido");
        }
        return futbolTeamMapper.futbolTeamEntityToDTO(futbolTeam.get());
    }

    @Override
    public List<FutbolTeamDTO> getFutbolTeamsByName(String nombre) throws ParseException {
        List<FutbolTeamEntity> entity = futbolTeamRepository.findAllByName(nombre);
        return futbolTeamMapper.futbolTeamEntityListToDTOList(entity);
    }

    @Override
    public FutbolTeamDTO save(FutbolTeamDTO futbolTeamDTO) throws ParseException {
        FutbolTeamEntity entity = futbolTeamMapper.futbolTeamDTOToEntity(futbolTeamDTO);
        FutbolTeamEntity entitySaved = futbolTeamRepository.save(entity);
        return futbolTeamMapper.futbolTeamEntityToDTO(entitySaved);
    }

    @Override
    public FutbolTeamDTO update(Long id, FutbolTeamDTO futbolTeamDTO) throws ParseException, Exceptions {
        Optional<FutbolTeamEntity> entity = futbolTeamRepository.findById(id);
        if (!entity.isPresent()){
            throw new Exceptions("Id del equipo no valido");
        }
        futbolTeamMapper.futbolTeamEntityUpdates(entity.get(), futbolTeamDTO);
        FutbolTeamEntity entitySaved = futbolTeamRepository.save(entity.get());
        return futbolTeamMapper.futbolTeamEntityToDTO(entitySaved);
    }

    @Override
    public void delete(Long id) throws ParseException {
        Optional<FutbolTeamEntity> entity = futbolTeamRepository.findById(id);
        if (entity.isPresent()){
            futbolTeamRepository.deleteById(id);
        }
    }
}