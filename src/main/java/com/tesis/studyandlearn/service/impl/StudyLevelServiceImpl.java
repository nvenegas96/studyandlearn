package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.LessonModalityEntity;
import com.tesis.studyandlearn.model.SpecialtyEntity;
import com.tesis.studyandlearn.model.StudyEntity;
import com.tesis.studyandlearn.model.dto.ChangeSpecialtyStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeStudyStatusDTO;
import com.tesis.studyandlearn.repository.LessonModalityRepository;
import com.tesis.studyandlearn.repository.StudyRepository;
import com.tesis.studyandlearn.service.StudyLevelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyLevelServiceImpl implements StudyLevelService {


    @Autowired
    private StudyRepository studyRepository;

    @Override
    public List<StudyEntity> showAll() {
        return studyRepository.findAll();
    }

    @Override
    public StudyEntity findStudyById(int studyId) {
        StudyEntity studyEntity = studyRepository.findById(studyId);
        StudyEntity studyEntity1 = new StudyEntity();
        BeanUtils.copyProperties(studyEntity, studyEntity1);
        return studyEntity1;
    }

    @Override
    public StudyEntity createOrUpdateStudy(StudyEntity studyEntity) {
        StudyEntity studyEntity1 = new StudyEntity();
        BeanUtils.copyProperties(studyEntity, studyEntity1);
        studyRepository.saveAndFlush(studyEntity);
        return studyEntity;
    }

    @Override
    public boolean checkCorrectEntity(StudyEntity studyEntity) {
        if (studyEntity.getName() == null || studyEntity.getName().isEmpty())
            return false;
        return true;
    }

    @Override
    public void changeStudyStatus(ChangeStudyStatusDTO changeStudyStatusDTO) {
        StudyEntity studyEntity = studyRepository.findById(changeStudyStatusDTO.getStudyId());
        if (studyEntity == null)
            throw new RuntimeException("Study modality not found!.");

        studyEntity.setEnabled(false);
        studyRepository.saveAndFlush(studyEntity);
    }
}
