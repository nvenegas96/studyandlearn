package com.tesis.studyandlearn.services.impl;

import com.tesis.studyandlearn.models.AlumnoEntity;
import com.tesis.studyandlearn.models.CoordinadorEntity;
import com.tesis.studyandlearn.repository.AlumnoRepository;
import com.tesis.studyandlearn.repository.CoordinadorRepository;
import com.tesis.studyandlearn.services.CoordinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinadorServiceImpl implements CoordinadorService {
    @Autowired
    private CoordinadorRepository coordinadorRepository;


    @Override
    public CoordinadorEntity findByID(int id_coordinador) {
        return coordinadorRepository.findById(id_coordinador).orElse(null);
    }

    @Override
    public void deletedByID(int id_coordinador) {
        coordinadorRepository.deleteById(id_coordinador);
    }

    @Override
    public void save(CoordinadorEntity coordinadorEntity) {
        coordinadorRepository.save(coordinadorEntity);
    }

    @Override
    public List<CoordinadorEntity> findALL() {
        return (List<CoordinadorEntity>) coordinadorRepository.findAll();
    }
}
