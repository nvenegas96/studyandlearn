package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.LessonCategoryEntity;
import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.dto.ChangeLessonCategoryStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeLessonStatusDTO;
import com.tesis.studyandlearn.model.dto.LessonDTO;
import com.tesis.studyandlearn.repository.LessonCategoryRepository;
import com.tesis.studyandlearn.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private LessonCategoryRepository lessonCategoryRepository;

    @Override
    public List<LessonCategoryEntity> showAllCategory() {
        return lessonCategoryRepository.findAllByEnabled(true);
    }

    @Override
    public LessonCategoryEntity showCategoryById(int categoryId) {
        return lessonCategoryRepository.findById(categoryId);
    }


    @Override
    public LessonCategoryEntity findCategoryById(int lessonCategoryId) {
        LessonCategoryEntity lessonCategoryEntity = lessonCategoryRepository.findById(lessonCategoryId);
        LessonCategoryEntity lessonCategoryEntity1 = new LessonCategoryEntity();
        BeanUtils.copyProperties(lessonCategoryEntity, lessonCategoryEntity1);
        return lessonCategoryEntity1;
    }

    @Override
    public LessonCategoryEntity createOrUpdateCategory(LessonCategoryEntity lessonCategoryEntity) {
        LessonCategoryEntity lessonCategoryEntity1 = new LessonCategoryEntity();
        BeanUtils.copyProperties(lessonCategoryEntity, lessonCategoryEntity1);
        lessonCategoryRepository.saveAndFlush(lessonCategoryEntity);
        return lessonCategoryEntity;
    }

    @Override
    public boolean checkCorrectEntity(LessonCategoryEntity lessonCategoryEntity) {
        if (lessonCategoryEntity.getName() == null || lessonCategoryEntity.getName().isEmpty())
            return false;
        return true;
    }

    @Override
    public void changeLessonCategoryStatus(ChangeLessonCategoryStatusDTO changeLessonCategoryStatusDTO) {
        LessonCategoryEntity lessonCategoryEntity = lessonCategoryRepository.findById(changeLessonCategoryStatusDTO.getLessonCategoryId());
        if (lessonCategoryEntity == null)
            throw new RuntimeException("Lesson category not found!.");

        lessonCategoryEntity.setEnabled(false);
        lessonCategoryRepository.saveAndFlush(lessonCategoryEntity);
    }


}
