package org.example.mapper;

import org.example.DTO.FutbolTeamDTO;
import org.example.entity.FutbolTeamEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FutbolTeamMapper {

    public FutbolTeamEntity futbolTeamDTOToEntity(FutbolTeamDTO dto) {
        FutbolTeamEntity entity = new FutbolTeamEntity();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setLiga(dto.getLiga());
        entity.setPais(dto.getPais());
        return entity;
    }

    public FutbolTeamDTO futbolTeamEntityToDTO(FutbolTeamEntity entity) {
        FutbolTeamDTO dto = new FutbolTeamDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setLiga(entity.getLiga());
        dto.setPais(entity.getPais());
        return dto;
    }

    public List<FutbolTeamDTO> futbolTeamEntityListToDTOList(List<FutbolTeamEntity> list) {
        List<FutbolTeamDTO> futbolTeamList = new ArrayList<>();
        for (FutbolTeamEntity entity : list) {
            futbolTeamList.add(futbolTeamEntityToDTO(entity));
        }
        return futbolTeamList;
    }

    public void futbolTeamEntityUpdates(FutbolTeamEntity entity, FutbolTeamDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setLiga(dto.getLiga());
        entity.setPais(dto.getPais());
    }
}