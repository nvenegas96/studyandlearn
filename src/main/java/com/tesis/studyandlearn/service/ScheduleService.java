package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.LessonScheduleEntity;
import com.tesis.studyandlearn.model.dto.LessonDTO;
import com.tesis.studyandlearn.model.dto.LessonScheduleDTO;
import com.tesis.studyandlearn.model.dto.ScheduleDTO;
import com.tesis.studyandlearn.model.dto.TeacherSchedule;

import java.util.List;

public interface ScheduleService {

    List<LessonScheduleEntity> showAllSchedule();

    LessonScheduleEntity createOrUpdateSchedule(LessonScheduleDTO lessonScheduleDTO);

    boolean checkCorrectDTO(LessonScheduleDTO lessonScheduleDTO);

    LessonScheduleDTO findByScheduleId(int lessonScheduleId);

    List<TeacherSchedule> showScheduleByLesson(int lessonId);


}
