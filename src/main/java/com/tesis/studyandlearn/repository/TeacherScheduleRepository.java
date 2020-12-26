package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonTeacherEntity;
import com.tesis.studyandlearn.model.TeacherScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherScheduleRepository extends JpaRepository<TeacherScheduleEntity, Integer> {

    List<TeacherScheduleEntity> findByLessonTeacherId(int lessonTeacherId);

    List<TeacherScheduleEntity> findAll();
}
