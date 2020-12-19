package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.models.AlumnoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends CrudRepository<AlumnoEntity,Integer> {

}
