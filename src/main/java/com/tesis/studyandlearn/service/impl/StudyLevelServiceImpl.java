package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.LessonModalityEntity;
import com.tesis.studyandlearn.model.StudyEntity;
import com.tesis.studyandlearn.repository.LessonModalityRepository;
import com.tesis.studyandlearn.repository.StudyRepository;
import com.tesis.studyandlearn.service.StudyLevelService;
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
}
