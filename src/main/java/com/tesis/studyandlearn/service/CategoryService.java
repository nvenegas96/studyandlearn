package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.LessonCategoryEntity;

import java.util.List;

public interface CategoryService {

    List<LessonCategoryEntity> showAllCategory();

    LessonCategoryEntity showCategoryById(int categoryId);
}
