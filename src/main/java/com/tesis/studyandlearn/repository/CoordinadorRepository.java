package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.models.CoordinadorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinadorRepository extends CrudRepository<CoordinadorEntity,Integer> {

}
