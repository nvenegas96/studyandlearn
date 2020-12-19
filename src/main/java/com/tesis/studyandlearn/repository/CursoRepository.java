package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.models.CursoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends CrudRepository<CursoEntity,Integer> {

}
