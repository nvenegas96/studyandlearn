package com.tesis.studyandlearn.services.impl;

import com.tesis.studyandlearn.models.AlumnoEntity;
import com.tesis.studyandlearn.repository.AlumnoRepository;
import com.tesis.studyandlearn.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;


    @Override
    public AlumnoEntity findByID(int id_alumno) {
        return alumnoRepository.findById(id_alumno).orElse(null);
    }

    @Override
    public void deletedByID(int id_alumno) {
        alumnoRepository.deleteById(id_alumno);
    }

    @Override
    public void save(AlumnoEntity alumnoEntity) {
        alumnoRepository.save(alumnoEntity);
    }

    @Override
    public List<AlumnoEntity> findALL() {
        return (List<AlumnoEntity>) alumnoRepository.findAll();
    }
}
