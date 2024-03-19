package org.example.repository;

import org.example.entity.FutbolTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FutbolTeamRepository extends JpaRepository<FutbolTeamEntity, Long>{

    @Query("select f from FutbolTeamEntity f where f.nombre like concat('%', ?1, '%')")
    List<FutbolTeamEntity> findAllByName(String nombre);
}