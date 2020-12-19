package com.tesis.studyandlearn.services;


import com.tesis.studyandlearn.models.AlumnoEntity;

import java.util.List;

public interface AlumnoService {

    public AlumnoEntity findByID(int id_alumno);
    public void deletedByID(int id_alumno);
    public void save(AlumnoEntity alumnoEntity);
    public List<AlumnoEntity> findALL();

}
