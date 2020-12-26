package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.LessonScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Integer> {

    List<LessonEntity> findAllByCategoryId(int categoryId);

    LessonEntity findById(int lessonId);

    List<LessonEntity> findAll();

}
