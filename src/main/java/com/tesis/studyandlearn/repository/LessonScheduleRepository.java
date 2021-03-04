package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonScheduleEntity;
import com.tesis.studyandlearn.model.constans.Querys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LessonScheduleRepository extends JpaRepository<LessonScheduleEntity, Integer> {

    List<LessonScheduleEntity> findAll();

    LessonScheduleEntity findById(int lessonScheduleId);

    LessonScheduleEntity findByLessonIdAndTeacherIdAndDate(int lessonId, int teacherId, Date date);

    List<LessonScheduleEntity> findAllByTeacherId(int teacherId);

    List<LessonScheduleEntity> findAllByStudentId(int studentId);

    @Query(value = Querys.assessmentProm, nativeQuery = true)
    Integer showAssessmentByLessonAndTeacher(int lessonId, int teacherId);

}

