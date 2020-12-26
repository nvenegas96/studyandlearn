package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.LessonModalityEntity;
import com.tesis.studyandlearn.model.LessonTeacherEntity;
import com.tesis.studyandlearn.repository.LessonTeacherRepository;
import com.tesis.studyandlearn.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {


    @Autowired
    private LessonTeacherRepository lessonTeacherRepository;

    @Override
    public List<LessonTeacherEntity> showAll() {
        return lessonTeacherRepository.findAll();
    }
}
