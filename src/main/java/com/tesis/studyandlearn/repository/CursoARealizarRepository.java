package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.models.CursoARealizarEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CursoARealizarRepository extends CrudRepository<CursoARealizarEntity, Integer> {

    @Modifying
    @Query(value="UPDATE curso_a_realizar c SET c.estado =:estado WHERE c.id_clase =:id", nativeQuery = true)
    @Transactional
    void actualizarEstado(@Param("estado") String estado, @Param("id") Integer id);
}
