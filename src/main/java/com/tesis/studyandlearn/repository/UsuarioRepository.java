package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.models.CoordinadorEntity;
import com.tesis.studyandlearn.models.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Integer> {

    public UsuarioEntity findByUsername(String username);
}
