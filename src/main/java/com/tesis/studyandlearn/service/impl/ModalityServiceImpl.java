package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.LessonCategoryEntity;
import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.LessonModalityEntity;
import com.tesis.studyandlearn.model.dto.ChangeLessonModalityStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeLessonStatusDTO;
import com.tesis.studyandlearn.repository.LessonModalityRepository;
import com.tesis.studyandlearn.service.ModalityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModalityServiceImpl implements ModalityService {

    @Autowired
    private LessonModalityRepository modalityRepository;

    @Override
    public List<LessonModalityEntity> showAll() {
        return modalityRepository.findAll();
    }


    @Override
    public LessonModalityEntity findModalityById(int lessonModalityId) {
        LessonModalityEntity lessonModalityEntity = modalityRepository.findById(lessonModalityId);
        LessonModalityEntity lessonModalityEntity1 = new LessonModalityEntity();
        BeanUtils.copyProperties(lessonModalityEntity, lessonModalityEntity1);
        return lessonModalityEntity1;
    }

    @Override
    public LessonModalityEntity createOrUpdateModality(LessonModalityEntity lessonModalityEntity) {
        LessonModalityEntity lessonModalityEntity1 = new LessonModalityEntity();
        BeanUtils.copyProperties(lessonModalityEntity, lessonModalityEntity1);
        modalityRepository.saveAndFlush(lessonModalityEntity);
        return lessonModalityEntity;
    }

    @Override
    public boolean checkCorrectEntity(LessonModalityEntity lessonModalityEntity) {
        if (lessonModalityEntity.getName() == null || lessonModalityEntity.getName().isEmpty())
            return false;
        return true;
    }

    @Override
    public void changeLessonModalityStatus(ChangeLessonModalityStatusDTO changeLessonModalityStatusDTO) {
        LessonModalityEntity lessonModalityEntity = modalityRepository.findById(changeLessonModalityStatusDTO.getLessonModalityId());
        if (lessonModalityEntity == null)
            throw new RuntimeException("Lesson modality not found!.");

        lessonModalityEntity.setEnabled(false);
        modalityRepository.saveAndFlush(lessonModalityEntity);
    }
}
