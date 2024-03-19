package org.example.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FutbolTeamDTO {
    Long id;
    String nombre;
    String liga;
    String pais;
}