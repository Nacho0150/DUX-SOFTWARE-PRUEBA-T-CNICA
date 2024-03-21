package org.example.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FutbolTeamDTO {
    @Schema(name = "id", description = "ID del equipo", example = "25", type = "String", hidden = true)
    Long id;
    @Schema(name = "nombre", description = "Nombre del equipo", example = "Nuevo Equipo FC", type = "String")
    String nombre;
    @Schema(name = "liga", description = "Liga del equipo", example = "Nueva Liga", type = "String")
    String liga;
    @Schema(name = "pais", description = "País del equipo", example = "Nuevo País", type = "String")
    String pais;
}