package com.tesis.studyandlearn.services;

import com.tesis.studyandlearn.models.AlumnoEntity;
import com.tesis.studyandlearn.models.DocenteEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocenteService {

    public DocenteEntity findByID(int id_docente);
    public void deletedByID(int id_docente);
    public void save(DocenteEntity docenteEntity);
    public List<DocenteEntity> findALL();
    public void actualizarRegistro_Clases(@Param("registro_clases") int registro_clases, @Param("id") Integer id);
}
