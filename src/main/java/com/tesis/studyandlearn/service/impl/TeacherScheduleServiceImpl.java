package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.*;
import com.tesis.studyandlearn.model.dto.LessonDTO;
import com.tesis.studyandlearn.model.dto.LessonScheduleDTO;
import com.tesis.studyandlearn.repository.LessonRepository;
import com.tesis.studyandlearn.repository.LessonTeacherRepository;
import com.tesis.studyandlearn.repository.TeacherScheduleRepository;
import com.tesis.studyandlearn.service.TeacherScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherScheduleServiceImpl implements TeacherScheduleService {

    @Autowired
    private TeacherScheduleRepository teacherScheduleRepository;

    @Override
    public List<TeacherScheduleEntity> showAll() {
        return teacherScheduleRepository.findAll();
    }

}
