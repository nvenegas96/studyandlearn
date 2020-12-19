package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.models.DocenteEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DocenteRepository extends CrudRepository<DocenteEntity,Integer> {

    @Modifying
    @Query(value="UPDATE docente d SET d.registro_clases =:registro_clases WHERE d.id_docente=:id", nativeQuery = true)
    @Transactional
    void actualizarRegistro_Clases(@Param("registro_clases") int registro_clases, @Param("id") Integer id);

}
