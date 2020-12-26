package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.LessonModalityEntity;
import com.tesis.studyandlearn.repository.LessonModalityRepository;
import com.tesis.studyandlearn.service.ModalityService;
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
}
