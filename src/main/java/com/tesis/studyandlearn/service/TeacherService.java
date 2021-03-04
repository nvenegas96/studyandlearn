package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.LessonTeacherEntity;
import com.tesis.studyandlearn.model.SpecialtyEntity;
import com.tesis.studyandlearn.model.dto.ChangeLessonStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeLessonTeacherStatusDTO;
import com.tesis.studyandlearn.model.dto.LessonDTO;
import com.tesis.studyandlearn.model.dto.LessonTeacherDTO;

import java.util.List;

public interface TeacherService {

    List<LessonTeacherEntity> showAll();

    LessonTeacherEntity createOrUpdateLessonTeacher(LessonTeacherDTO lessonTeacherDTO);

    boolean checkCorrectDTO(LessonTeacherDTO lessonTeacherDTO);

    LessonTeacherDTO findByLessonTeacherId(int lessonTeacherId);

    List<LessonTeacherDTO> showAllLessonTeacherDTO();

    List<LessonTeacherDTO> showAllByTeacherId(int teacherId);

    void changeLessonTeacherStatus(ChangeLessonTeacherStatusDTO changeLessonTeacherStatusDTO);


}
