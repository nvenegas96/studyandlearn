package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.LessonTeacherEntity;
import com.tesis.studyandlearn.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonTeacherRepository extends JpaRepository<LessonTeacherEntity, Integer> {

    List<LessonTeacherEntity> findAll();

    List<LessonTeacherEntity> findAllByLessonId(int lessonId);

    LessonTeacherEntity findById(int lessonTeacherId);

    LessonTeacherEntity findByLessonIdAndUserId(int lessonId, int teacherId);

    List<LessonTeacherEntity> findAllByUserId(int userId);
}
