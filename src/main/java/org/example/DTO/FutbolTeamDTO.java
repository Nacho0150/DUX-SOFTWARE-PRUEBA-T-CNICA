package org.example.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FutbolTeamDTO {
    @Schema(name = "id", description = "ID del equipo", example = "1", type = "Long", hidden = true)
    Long id;
    @Schema(name = "nombre", description = "Nombre del equipo", example = "Real Madrid", type = "String", hidden = true)
    String nombre;
    @Schema(name = "liga", description = "Liga del equipo", example = "La Liga", type = "String", hidden = true)
    String liga;
    @Schema(name = "pais", description = "País del equipo", example = "España", type = "String", hidden = true)
    String pais;
}