package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.LessonCategoryEntity;
import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.dto.ChangeLessonCategoryStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeLessonStatusDTO;
import com.tesis.studyandlearn.model.dto.LessonDTO;

import java.util.List;

public interface CategoryService {

    List<LessonCategoryEntity> showAllCategory();

    LessonCategoryEntity showCategoryById(int categoryId);

    LessonCategoryEntity findCategoryById(int lessonCategoryId);

    LessonCategoryEntity createOrUpdateCategory(LessonCategoryEntity lessonCategoryEntity);

    boolean checkCorrectEntity(LessonCategoryEntity lessonCategoryEntity);

    void changeLessonCategoryStatus(ChangeLessonCategoryStatusDTO changeLessonCategoryStatusDTO);
}

