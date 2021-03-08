package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.LessonModalityEntity;
import com.tesis.studyandlearn.model.SpecialtyEntity;
import com.tesis.studyandlearn.model.UserSpecialtyEntity;
import com.tesis.studyandlearn.model.dto.ChangeLessonModalityStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeSpecialtyStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeUserSpecialtyStatusDTO;
import com.tesis.studyandlearn.repository.SpecialtyRepository;
import com.tesis.studyandlearn.service.SpecialtyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public List<SpecialtyEntity> showAllSpecialty() {
        return specialtyRepository.findAll();
    }


    @Override
    public SpecialtyEntity findSpecialtyById(int specialtyId) {
        SpecialtyEntity specialtyEntity = specialtyRepository.findById(specialtyId);
        SpecialtyEntity specialtyEntity1 = new SpecialtyEntity();
        BeanUtils.copyProperties(specialtyEntity, specialtyEntity1);
        return specialtyEntity1;
    }

    @Override
    public SpecialtyEntity createOrUpdateSpecialty(SpecialtyEntity specialtyEntity) {
        SpecialtyEntity specialtyEntity1 = new SpecialtyEntity();
        BeanUtils.copyProperties(specialtyEntity, specialtyEntity1);
        specialtyRepository.saveAndFlush(specialtyEntity);
        return specialtyEntity;
    }

    @Override
    public boolean checkCorrectEntity(SpecialtyEntity specialtyEntity) {
        if (specialtyEntity.getName() == null || specialtyEntity.getName().isEmpty())
            return false;

        return true;
    }

    @Override
    public void changeSpecialtyStatus(ChangeSpecialtyStatusDTO changeSpecialtyStatusDTO) {
        SpecialtyEntity specialtyEntity = specialtyRepository.findById(changeSpecialtyStatusDTO.getSpecialtyId());
        if (specialtyEntity == null)
            throw new RuntimeException("Specialty modality not found!.");

        specialtyEntity.setEnabled(false);
        specialtyRepository.saveAndFlush(specialtyEntity);
    }
}
