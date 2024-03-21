package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.DTO.FutbolTeamDTO;
import org.example.exception.MessageInfo;
import org.example.exception.Exceptions;
import org.example.service.FutbolTeamService;
import org.example.utils.SwaggerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.events.EventException;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipos")
@Tag(name = "Equipos", description = "Crea, muestra, elimina y actualiza Equipos")
public class TeamController {

    @Autowired
    private FutbolTeamService futbolTeamService;

    private static final String ERROR = "La solicitud es invalida: ";

    @Tag(name = "Equipos")
    @Operation(summary = "Obtención de todos los equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve la lista de todos los equipos de fútbol registrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInfo.class),
                            array = @ArraySchema(schema = @Schema(implementation = String.class)),
                            examples = {
                                    @ExampleObject(name = "Respuesta Exitosa(200 OK)", summary = "200 OK", value = SwaggerConstants.MESSAGE_LIST),
                            })}),
            @ApiResponse(responseCode = "403", description = "No tienes permisos", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<?>> getAllFutbolTeams() throws Exceptions { //PARA OBTENER TODOS LOS EQUIPOS DE FUTBOL
        try {
            List<FutbolTeamDTO> futbolTeams = futbolTeamService.getAllFutbolTeams();
            return ResponseEntity.status(HttpStatus.OK).body(futbolTeams);
        } catch (EventException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonList(ERROR + e.getMessage()));
        }
    }

    @Tag(name = "Equipos")
    @Operation(summary = "Obtención de un equipo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve la información del equipo correspondiente al ID proporcionado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInfo.class),
                            examples =
                                    @ExampleObject(name = "Respuesta Exitosa(200 OK)", summary = "200 OK", value = SwaggerConstants.MESSAGE_BY_ID)
                            )}),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = { @Content(
                    schema = @Schema(implementation = MessageInfo.class),
                    examples = @ExampleObject(name = "Respuesta Error (404 Not Found)", summary = "404 Not Found", value = SwaggerConstants.MESSAGE_ERROR_404))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getFutbolTeamById(@PathVariable Long id) throws Exceptions { //PARA OBTENER A UN EQUIPO POR ID
        try {
            FutbolTeamDTO futbolTeam = futbolTeamService.getFutbolTeamById(id);
            return ResponseEntity.status(HttpStatus.OK).body(futbolTeam);
        } catch (Exceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(SwaggerConstants.MESSAGE_ERROR_404);
        }
    }

    @Tag(name = "Equipos")
    @Operation(summary = "Obtención de lista o un equipo por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve la lista de equipos cuyos nombres contienen el valor proporcionado en el parámetro de búsqueda",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageInfo.class),
                            array = @ArraySchema(schema = @Schema(implementation = String.class)),
                            examples = {
                                    @ExampleObject(name = "Respuesta Exitosa (200 OK)", summary = "200 OK", value = SwaggerConstants.MESSAGE_LIST_BY_NAME),
                            })}),
            @ApiResponse(responseCode = "400", description = "Ingreso de datos inválidos", content = @Content)
    })
    @GetMapping("/buscar")
    public ResponseEntity<List<?>> getFutbolTeamsByName(@RequestParam(required = false) String nombre) { //PARA OBTENER A UN EQUIPO POR NOMBRE
        try {
            List<FutbolTeamDTO> futbolTeams = futbolTeamService.getFutbolTeamsByName(nombre);
            return ResponseEntity.status(HttpStatus.OK).body(futbolTeams);
        } catch (Exceptions e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonList(ERROR + e.getMessage()));
        }
    }

    @Tag(name = "Equipos")
    @Operation(summary = "Creación de equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "El equipo fue creado con éxito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FutbolTeamDTO.class),
                            examples = {
                                    @ExampleObject(name = "Respuesta Exitosa (201 Created)", summary = "201 Created", value = SwaggerConstants.MESSAGE_CREATE),
                    })}),
            @ApiResponse(responseCode = "400", description = "La solicitud es invalida",
                    content = { @Content(
                        schema = @Schema(implementation = MessageInfo.class),
                        examples = {
                                @ExampleObject(name = "Respuesta error (400 Bad Request)", summary = "400 Bad Request", value = SwaggerConstants.MESSAGE_ERROR_400),
                    })})
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody FutbolTeamDTO futbolTeam) { //PARA GUARDAR LOS EQUIPOS
        try {
            FutbolTeamDTO futbolTeamSave = futbolTeamService.save(futbolTeam);
            return ResponseEntity.status(HttpStatus.CREATED).body(futbolTeamSave);
        } catch (Exceptions e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(SwaggerConstants.MESSAGE_ERROR_400);
        }
    }

    @Tag(name = "Equipos")
    @Operation(summary = "Actualizacion de equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve la información actualizada del equipo",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FutbolTeamDTO.class),
                            examples = {
                                    @ExampleObject(name = "Respuesta Exitosa (200 OK)", summary = "200 OK", value = SwaggerConstants.MESSAGE_CREATE),
                    })}),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado",
                    content = { @Content(
                            schema = @Schema(implementation = MessageInfo.class),
                            examples = {
                                    @ExampleObject(name = "Respuesta error (404 Not Found)", summary = "404 Not Found", value = SwaggerConstants.MESSAGE_ERROR_404),
                    })})
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FutbolTeamDTO futbolTeam) throws Exceptions { //PARA ACTUALIZAR LOS EQUIPOS
        try {
            FutbolTeamDTO futbolTeamUpdate = futbolTeamService.update(id, futbolTeam);
            return ResponseEntity.status(HttpStatus.OK).body(futbolTeamUpdate);
        } catch (Exceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(SwaggerConstants.MESSAGE_ERROR_404);
        }
    }

    @Tag(name = "Equipos")
    @Operation(summary = "Eliminación de un equipo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "El equipo fue eliminado con éxito",
                    content = { @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "Respuesta Exitosa (204 No Content)", summary = "204 No Content")})}),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado",
                    content = {@Content(
                            schema = @Schema(implementation = MessageInfo.class),
                            examples = @ExampleObject(name = "Respuesta Error (404 Not Found)", summary = "404 Not Found", value = SwaggerConstants.MESSAGE_ERROR_404))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exceptions { //PARA ELIMINAR LOS EQUIPOS
        try {
            futbolTeamService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(SwaggerConstants.MESSAGE_ERROR_404);
        }
    }
}