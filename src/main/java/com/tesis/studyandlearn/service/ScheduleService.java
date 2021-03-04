package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.LessonScheduleEntity;
import com.tesis.studyandlearn.model.dto.*;

import java.util.List;

public interface ScheduleService {

    List<LessonScheduleEntity> showAllSchedule();


    LessonScheduleEntity createOrUpdateSchedule(LessonScheduleDTO lessonScheduleDTO);

    boolean checkCorrectDTO(LessonScheduleDTO lessonScheduleDTO);

    LessonScheduleDTO findByScheduleId(int lessonScheduleId);

    List<TeacherSchedule> showScheduleByLesson(int lessonId);

    boolean createReserve(int lessonId, int teacherId, String[] hours, Integer userId);

    List<LessonScheduleDTO> showAllLessonScheduleDTOByTeacherEmail();

    List<LessonScheduleDTO> showAllLessonScheduleDTOByTeacherEmail(String userEmail);

    List<LessonScheduleDTO> showAllLessonScheduleDTOByStudentEmail(String userEmail);

    void changeScheduleStatus(ChangeScheduleStatusDTO changeScheduleStatusDTO);

    void changeAssessment(ChangeLessonScheduleAssessmentDTO changeLessonScheduleAssessmentDTO);

}
