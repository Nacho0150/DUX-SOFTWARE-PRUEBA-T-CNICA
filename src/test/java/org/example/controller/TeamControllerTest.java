package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTO.FutbolTeamDTO;
import org.example.entity.FutbolTeamEntity;
import org.example.exception.Exceptions;
import org.example.mapper.FutbolTeamMapper;
import org.example.repository.FutbolTeamRepository;
import org.example.security.controller.utils.AuthBasic;
import org.example.service.impl.FutbolTeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
class TeamControllerTest extends AuthBasic {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private FutbolTeamServiceImpl futbolTeamService;

    @MockBean
    private FutbolTeamRepository futbolTeamRepository;

    @Autowired
    FutbolTeamMapper futbolTeamMapper;

    List<FutbolTeamEntity> teamEntityList = new ArrayList<>();
    List<FutbolTeamDTO> futbolTeamDTOList = new ArrayList<>();

    @Autowired
    ObjectMapper objectMapper;

    private FutbolTeamEntity futbolTeam;
    private FutbolTeamDTO futbolTeamDTO;
    private FutbolTeamDTO futbolTeamDTOResponce;

    private WebRequest request = null;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        futbolTeam = new FutbolTeamEntity();
        futbolTeam.setId(1L);
        futbolTeam.setNombre("name");
        futbolTeam.setLiga("liga");
        futbolTeam.setPais("pais");
        teamEntityList.add(futbolTeam);

        futbolTeamDTO = new FutbolTeamDTO();
        futbolTeamDTO.setId(1L);
        futbolTeamDTO.setNombre("name");
        futbolTeamDTO.setLiga("liga");
        futbolTeamDTO.setPais("pais");
        futbolTeamDTOList.add(futbolTeamDTO);
    }

    @Test
    void getAllFutbolTeams() throws Exception {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.setRequestURI("/equipos");
        WebRequest webRequest = new ServletWebRequest(servletRequest);
        when(futbolTeamService.getAllFutbolTeams()).thenReturn(futbolTeamDTOList);
        mockMvc.perform(get("/equipos")
                .contentType(APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwt)
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void getAllFutbolTeamsForbidden() throws Exception {
        when(futbolTeamService.getAllFutbolTeams()).thenThrow((new ResponseStatusException(HttpStatus.FORBIDDEN)));
        mockMvc.perform(get("/equipos")
                .contentType(APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwt)
                .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    void getFutbolTeamById() throws Exception {
        when(futbolTeamService.getFutbolTeamById(futbolTeam.getId())).thenReturn(futbolTeamDTO);
        mockMvc.perform(get("/equipos/{id}", futbolTeam.getId())
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void getFutbolTeamByIdNotFound() throws Exception {
        Long id = 100L;
        when(futbolTeamService.getFutbolTeamById(id)).thenThrow((new ResponseStatusException(HttpStatus.NOT_FOUND)));
        mockMvc.perform(get("/equipos/{id}", id)
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getFutbolTeamsByName() throws Exception {
        when(futbolTeamService.getFutbolTeamsByName(futbolTeam.getNombre())).thenReturn(futbolTeamDTOList);
        mockMvc.perform(get("/equipos/buscar", futbolTeam.getNombre())
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void getFutbolTeamsByNameBadRequest() throws Exception {
        String teamName = null;
        when(futbolTeamService.getFutbolTeamsByName(teamName)).thenThrow((new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        mockMvc.perform(get("/equipos/buscar", teamName)
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void save() throws Exception {
        when(futbolTeamService.save(futbolTeamDTO)).thenReturn(futbolTeamDTO);
        mockMvc.perform(post("/equipos")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(futbolTeamDTO)) // Convertir el DTO a JSON
                        .header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    void saveBadRequest() throws Exception {
        futbolTeamDTOResponce = null;
        mockMvc.perform(post("/equipos")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(futbolTeamDTOResponce)) // Convertir el DTO a JSON
                        .header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update() throws Exception {
        when(futbolTeamService.update(futbolTeam.getId(), futbolTeamDTO)).thenReturn(futbolTeamDTO);
        mockMvc.perform(put("/equipos/{id}", futbolTeam.getId())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(futbolTeamDTO)) // Convertir el DTO a JSON
                        .header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void updateNotFound() throws Exception {
        Long id = 1L;
        mockMvc.perform(put("/equipos/{id}", id)
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    void deletes() throws Exception {
        mockMvc.perform(delete("/equipos/{id}", futbolTeam.getId())
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletesnNotFound() throws Exception {
        Long id = 100L;
        doThrow(Exceptions.class).when(futbolTeamService).delete(id);
        mockMvc.perform(delete("/equipos/{id}", id)
                        .contentType(APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwt)
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }
}