package com.tesis.studyandlearn.services.impl;

import com.tesis.studyandlearn.models.DocenteEntity;
import com.tesis.studyandlearn.repository.DocenteRepository;
import com.tesis.studyandlearn.services.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Override
    public DocenteEntity findByID(int id_docente) {
        return docenteRepository.findById(id_docente).orElse(null);
    }

    @Override
    public void deletedByID(int id_docente) {
        docenteRepository.deleteById(id_docente);
    }

    @Override
    public void save(DocenteEntity docenteEntity) {
        docenteRepository.save(docenteEntity);
    }

    @Override
    public List<DocenteEntity> findALL() {
        return (List<DocenteEntity>) docenteRepository.findAll();
    }

    @Override
    public void actualizarRegistro_Clases(int registro_clases, Integer id) {
            docenteRepository.actualizarRegistro_Clases(registro_clases, id);
    }
}
