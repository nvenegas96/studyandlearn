package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.LessonStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonStatusRepository extends JpaRepository<LessonStatusEntity, Integer> {

    List<LessonStatusEntity> findAll();

    LessonStatusEntity findById(int id);
}
