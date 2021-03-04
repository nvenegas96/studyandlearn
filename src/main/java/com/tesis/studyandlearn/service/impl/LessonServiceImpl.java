package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.*;
import com.tesis.studyandlearn.model.dto.ChangeLessonStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeScheduleStatusDTO;
import com.tesis.studyandlearn.model.dto.LessonDTO;
import com.tesis.studyandlearn.repository.LessonCategoryRepository;
import com.tesis.studyandlearn.repository.LessonModalityRepository;
import com.tesis.studyandlearn.repository.LessonRepository;
import com.tesis.studyandlearn.repository.LessonScheduleRepository;
import com.tesis.studyandlearn.service.LessonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {


    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonScheduleRepository lessonScheduleRepository;

    @Autowired
    private LessonModalityRepository lessonModalityRepository;

    @Autowired
    private LessonCategoryRepository lessonCategoryRepository;

    @Override
    public List<LessonEntity> showAllByCategory(int categoryId) {
        return lessonRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public LessonEntity showLessonById(int lessonId) {
        return lessonRepository.findById(lessonId);
    }

    @Override
    public List<LessonEntity> showAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public List<LessonDTO> showAllLessonDTO() {
        List<LessonEntity> lessonEntities = lessonRepository.findAll();
        List<LessonModalityEntity> lessonModalityEntities = lessonModalityRepository.findAll();
        List<LessonCategoryEntity> lessonCategoryEntities = lessonCategoryRepository.findAll();
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        for(LessonEntity lessonEntity : lessonEntities){
            LessonDTO lessonDTO = new LessonDTO();
            BeanUtils.copyProperties(lessonEntity, lessonDTO);
            lessonDTO.setModalityName(getModalityName(lessonModalityEntities, lessonDTO.getModalityId()));
            lessonDTO.setCategoryName(getCategoryName(lessonCategoryEntities, lessonDTO.getCategoryId()));
            lessonDTOS.add(lessonDTO);
        }
        return lessonDTOS;
    }

    private String getModalityName(List<LessonModalityEntity> lessonModalityEntities, int modalityId){
        for(LessonModalityEntity lessonModalityEntity : lessonModalityEntities){
            if(lessonModalityEntity.getId() == modalityId)
                return lessonModalityEntity.getName();
        }
        return null;
    }

    private String getCategoryName(List<LessonCategoryEntity> lessonCategoryEntities, int categoryId){
        for(LessonCategoryEntity lessonCategoryEntity : lessonCategoryEntities){
            if(lessonCategoryEntity.getId() == categoryId)
                return lessonCategoryEntity.getName();
        }
        return null;
    }


    @Override
    public LessonEntity createOrUpdateLesson(LessonDTO lessonDTO) {
        LessonEntity lessonEntity = new LessonEntity();
        BeanUtils.copyProperties(lessonDTO, lessonEntity);
        lessonRepository.saveAndFlush(lessonEntity);
        return lessonEntity;
    }

    @Override
    public boolean checkCorrectDTO(LessonDTO lessonDTO) {
        if (lessonDTO.getName() == null || lessonDTO.getName().isEmpty())
            return false;
        if (lessonDTO.getCategoryId() == null)
            return false;
        if (lessonDTO.getModalityId() == null)
            return false;
        return true;
    }

    @Override
    public LessonDTO findByLessonId(int lessonId) {
        LessonEntity lessonEntity = lessonRepository.findById(lessonId);
        LessonDTO lessonDTO = new LessonDTO();
        BeanUtils.copyProperties(lessonEntity, lessonDTO);
        return lessonDTO;
    }

    @Override
    public void changeLessonStatus(ChangeLessonStatusDTO changeLessonStatusDTO) {
        LessonEntity lessonEntity = lessonRepository.findById(changeLessonStatusDTO.getLessonId());
        if (lessonEntity == null)
            throw new RuntimeException("Lesson not found!.");

        lessonEntity.setEnabled(false);
        lessonRepository.saveAndFlush(lessonEntity);
    }

}
