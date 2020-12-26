package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonTeacherEntity;
import com.tesis.studyandlearn.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonTeacherRepository extends JpaRepository<LessonTeacherEntity, Integer> {

    List<LessonTeacherEntity> findAll();

    List<LessonTeacherEntity> findAllByLessonId(int lessonId);

    LessonTeacherEntity findByLessonIdAndUserId(int lessonId, int teacherId);
}
