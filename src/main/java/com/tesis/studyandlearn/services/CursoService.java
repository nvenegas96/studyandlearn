package com.tesis.studyandlearn.services;

import com.tesis.studyandlearn.models.AlumnoEntity;
import com.tesis.studyandlearn.models.CursoEntity;

import java.util.List;

public interface CursoService {

    public CursoEntity findByID(int id_curso);
    public void deletedByID(int id_curso);
    public void save(CursoEntity cursoEntity);
    public List<CursoEntity> findALL();
}
