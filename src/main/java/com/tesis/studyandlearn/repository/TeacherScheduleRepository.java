package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonCategoryEntity;
import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.LessonScheduleEntity;
import com.tesis.studyandlearn.model.TeacherScheduleEntity;
import com.tesis.studyandlearn.model.dto.TeacherSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherScheduleRepository extends JpaRepository<TeacherScheduleEntity, Integer> {

    List<TeacherScheduleEntity> findAllByLessonTeacherId(int lessonTeacherId);

    List<TeacherScheduleEntity> findAllByLessonTeacherIdIn(List<Integer> lessonTeacherIds);

    TeacherScheduleEntity findById(int teacherScheduleId);

    List<TeacherScheduleEntity> findAll();

}
