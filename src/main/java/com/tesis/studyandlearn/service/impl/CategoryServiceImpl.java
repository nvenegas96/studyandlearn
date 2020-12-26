package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.LessonCategoryEntity;
import com.tesis.studyandlearn.repository.LessonCategoryRepository;
import com.tesis.studyandlearn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private LessonCategoryRepository lessonCategoryRepository;

    @Override
    public List<LessonCategoryEntity> showAllCategory() {
        return lessonCategoryRepository.findAllByEnable(true);
    }

    @Override
    public LessonCategoryEntity showCategoryById(int categoryId) {
        return lessonCategoryRepository.findById(categoryId);
    }


}
