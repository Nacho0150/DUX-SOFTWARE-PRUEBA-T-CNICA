package org.example.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FutbolTeamDTO {
    @Schema(name = "id", description = "ID del equipo", example = "2", type = "Long")
    Long id;
    @Schema(name = "nombre", description = "Nombre del equipo", example = "Barcelona FC", type = "String")
    String nombre;
    @Schema(name = "liga", description = "Liga del equipo", example = "La Liga", type = "String")
    String liga;
    @Schema(name = "pais", description = "País del equipo", example = "España", type = "String")
    String pais;
}