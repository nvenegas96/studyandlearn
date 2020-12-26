package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.LessonScheduleEntity;
import com.tesis.studyandlearn.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonScheduleRepository extends JpaRepository<LessonScheduleEntity, Integer> {

    List<LessonScheduleEntity> findAll();

    LessonScheduleEntity findById(int lessonScheduleId);

}

