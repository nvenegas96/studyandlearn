package com.tesis.studyandlearn.services.impl;

import com.tesis.studyandlearn.models.CursoARealizarEntity;
import com.tesis.studyandlearn.repository.CursoARealizarRepository;
import com.tesis.studyandlearn.services.CursoARealizarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoARealizarServiceImpl implements CursoARealizarService {

    @Autowired
    private CursoARealizarRepository cursoARealizarRepository;


    @Override
    public CursoARealizarEntity findByID(int id_clase) {
        return cursoARealizarRepository.findById(id_clase).orElse(null);
    }

    @Override
    public void deletedByID(int id_clase) {
        cursoARealizarRepository.deleteById(id_clase);
    }

    @Override
    public void save(CursoARealizarEntity cursoARealizarEntity) {
        cursoARealizarRepository.save(cursoARealizarEntity);
    }

    @Override
    public List<CursoARealizarEntity> findALL() {
        return (List<CursoARealizarEntity>) cursoARealizarRepository.findAll();
    }

    @Override
    public void actualizarEstado(String estado, Integer id) {
        cursoARealizarRepository.actualizarEstado(estado, id);
    }
}
