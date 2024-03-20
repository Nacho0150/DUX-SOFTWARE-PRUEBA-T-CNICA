package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTO.FutbolTeamDTO;
import org.example.entity.FutbolTeamEntity;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
    void getFutbolTeamById() {
    }

    @Test
    void getFutbolTeamsByName() {
    }

    @Test
    void save() {

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}