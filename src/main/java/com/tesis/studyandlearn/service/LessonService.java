package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.LessonCategoryEntity;
import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.LessonScheduleEntity;
import com.tesis.studyandlearn.model.dto.LessonDTO;

import java.util.List;

public interface LessonService {


    List<LessonEntity> showAllByCategory(int categoryId);

    LessonEntity showLessonById(int lessonId);

    List<LessonEntity> showAllLessons();

    List<LessonDTO> showAllLessonDTO();

    LessonEntity createOrUpdateLesson(LessonDTO lessonDTO);

    boolean checkCorrectDTO(LessonDTO lessonDTO);

    LessonDTO findByLessonId(int lessonId);

}
