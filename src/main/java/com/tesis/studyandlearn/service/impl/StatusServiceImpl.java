package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.LessonModalityEntity;
import com.tesis.studyandlearn.model.LessonStatusEntity;
import com.tesis.studyandlearn.model.dto.ChangeLessonModalityStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeLessonStatusesStatusDTO;
import com.tesis.studyandlearn.repository.LessonStatusRepository;
import com.tesis.studyandlearn.service.StatusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private LessonStatusRepository lessonStatusRepository;

    @Override
    public List<LessonStatusEntity> showAllStatus() {
        return lessonStatusRepository.findAll();
    }


    @Override
    public LessonStatusEntity findStatusById(int lessonStatusId) {
        LessonStatusEntity lessonStatusEntity = lessonStatusRepository.findById(lessonStatusId);
        LessonStatusEntity lessonModalityEntity1 = new LessonStatusEntity();
        BeanUtils.copyProperties(lessonStatusEntity, lessonModalityEntity1);
        return lessonModalityEntity1;
    }

    @Override
    public LessonStatusEntity createOrUpdateStatus(LessonStatusEntity lessonStatusEntity) {
        LessonStatusEntity lessonModalityEntity1 = new LessonStatusEntity();
        BeanUtils.copyProperties(lessonStatusEntity, lessonModalityEntity1);
        lessonStatusRepository.saveAndFlush(lessonStatusEntity);
        return lessonStatusEntity;
    }

    @Override
    public boolean checkCorrectEntity(LessonStatusEntity lessonStatusEntity) {
        if (lessonStatusEntity.getName() == null || lessonStatusEntity.getName().isEmpty())
            return false;
        return true;
    }

    @Override
    public void changeLessonStatusesStatus(ChangeLessonStatusesStatusDTO changeLessonStatusesStatusDTO) {
        LessonStatusEntity lessonStatusEntity = lessonStatusRepository.findById(changeLessonStatusesStatusDTO.getLessonStatusId());
        if (lessonStatusEntity == null)
            throw new RuntimeException("Lesson status not found!.");

        lessonStatusEntity.setEnabled(false);
        lessonStatusRepository.saveAndFlush(lessonStatusEntity);
    }
}
