package com.tesis.studyandlearn.services;

import com.tesis.studyandlearn.models.AlumnoEntity;
import com.tesis.studyandlearn.models.CursoARealizarEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoARealizarService {

    public CursoARealizarEntity findByID(int id_clase);
    public void deletedByID(int id_clase);
    public void save(CursoARealizarEntity cursoARealizarEntity);
    public List<CursoARealizarEntity> findALL();
    public void actualizarEstado(@Param("estado") String estado, @Param("id") Integer id);
}
