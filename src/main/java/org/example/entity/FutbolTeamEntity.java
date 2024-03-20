package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "equipos")
@AllArgsConstructor
@NoArgsConstructor
public class FutbolTeamEntity implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Liga")
    private String liga;

    @Column(name = "Pais")
    private String pais;
}