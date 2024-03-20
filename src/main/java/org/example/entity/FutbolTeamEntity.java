package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "equipos")
public class FutbolTeamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Liga")
    private String liga;

    @Column(name = "Pais")
    private String pais;
}