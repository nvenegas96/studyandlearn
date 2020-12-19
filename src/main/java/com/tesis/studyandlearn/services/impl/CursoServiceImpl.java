package com.tesis.studyandlearn.services.impl;

import com.tesis.studyandlearn.models.CursoEntity;
import com.tesis.studyandlearn.repository.CursoRepository;
import com.tesis.studyandlearn.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {


    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public CursoEntity findByID(int id_curso) {
        return cursoRepository.findById(id_curso).orElse(null);
    }


    @Override
    public void deletedByID(int id_curso) {
        cursoRepository.deleteById(id_curso);
    }

    @Override
    @Transactional
    public void save(CursoEntity cursoEntity) {
        if (cursoEntity.getId_curso() != null && cursoEntity.getId_curso() > 0){

        }
        cursoRepository.save(cursoEntity);
    }

    @Override
    public List<CursoEntity> findALL() {
        return (List<CursoEntity>) cursoRepository.findAll();
    }
}
