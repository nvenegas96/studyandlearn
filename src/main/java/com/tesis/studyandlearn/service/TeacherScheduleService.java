package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.LessonTeacherEntity;
import com.tesis.studyandlearn.model.TeacherScheduleEntity;
import com.tesis.studyandlearn.model.dto.LessonScheduleDTO;

import java.util.List;

public interface TeacherScheduleService {

    List<TeacherScheduleEntity> showAll();

}
