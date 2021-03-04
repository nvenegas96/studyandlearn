package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.*;
import com.tesis.studyandlearn.model.dto.ChangeLessonStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeLessonTeacherStatusDTO;
import com.tesis.studyandlearn.model.dto.LessonDTO;
import com.tesis.studyandlearn.model.dto.LessonTeacherDTO;
import com.tesis.studyandlearn.repository.LessonRepository;
import com.tesis.studyandlearn.repository.LessonTeacherRepository;
import com.tesis.studyandlearn.repository.UserRepository;
import com.tesis.studyandlearn.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {


    @Autowired
    private LessonTeacherRepository lessonTeacherRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<LessonTeacherEntity> showAll() {
        return lessonTeacherRepository.findAll();
    }


    @Override
    public List<LessonTeacherDTO> showAllLessonTeacherDTO() {
        List<LessonTeacherEntity> lessonTeacherEntities = lessonTeacherRepository.findAll();
        List<LessonEntity> lessonEntities = lessonRepository.findAll();
        List<UserEntity> userEntities = userRepository.findAll();
        List<LessonTeacherDTO> lessonTeacherDTOS = new ArrayList<>();
        for(LessonTeacherEntity lessonTeacherEntity : lessonTeacherEntities){
            LessonTeacherDTO lessonTeacherDTO = new LessonTeacherDTO();
            BeanUtils.copyProperties(lessonTeacherEntity, lessonTeacherDTO);
            lessonTeacherDTO.setLessonName(getLessonName(lessonEntities, lessonTeacherDTO.getLessonId()));
            lessonTeacherDTO.setUserName(getUserName(userEntities, lessonTeacherDTO.getUserId()));
            lessonTeacherDTOS.add(lessonTeacherDTO);
        }
        return lessonTeacherDTOS;
    }

    @Override
    public List<LessonTeacherDTO> showAllByTeacherId(int teacherId) {
        List<LessonTeacherEntity> lessonTeacherEntities = lessonTeacherRepository.findAllByUserId(teacherId);
        List<LessonTeacherDTO> lessonTeacherDTOS = new ArrayList<>();
        for (LessonTeacherEntity lessonTeacherEntity : lessonTeacherEntities) {
            LessonTeacherDTO lessonTeacherDTO = new LessonTeacherDTO();
            LessonEntity lessonEntity = lessonRepository.findById(lessonTeacherEntity.getLessonId().intValue());
            lessonTeacherDTO.setId(lessonTeacherEntity.getId());
            lessonTeacherDTO.setLessonId(lessonEntity.getId());
            lessonTeacherDTO.setLessonName(lessonEntity.getName());
            lessonTeacherDTO.setUserId(teacherId);
            lessonTeacherDTO.setEnabled(lessonEntity.isEnabled());
            lessonTeacherDTOS.add(lessonTeacherDTO);

        }
        return lessonTeacherDTOS;
    }

    private String getLessonName(List<LessonEntity> lessonEntities, int lessonId){
        for(LessonEntity lessonEntity : lessonEntities){
            if(lessonEntity.getId() == lessonId)
                return lessonEntity.getName();
        }
        return null;
    }

    private String getUserName(List<UserEntity> userEntities, int userId){
        for(UserEntity userEntity : userEntities){
            if(userEntity.getId() == userId)
                return userEntity.getName();
        }
        return null;
    }


    @Override
    public LessonTeacherEntity createOrUpdateLessonTeacher(LessonTeacherDTO lessonTeacherDTO) {
        LessonTeacherEntity lessonTeacherEntity = new LessonTeacherEntity();
        BeanUtils.copyProperties(lessonTeacherDTO, lessonTeacherEntity);
        lessonTeacherRepository.saveAndFlush(lessonTeacherEntity);
        return lessonTeacherEntity;
    }

    @Override
    public boolean checkCorrectDTO(LessonTeacherDTO lessonTeacherDTO) {
        if (lessonTeacherDTO.getLessonId() == null)
            return false;
        if (lessonTeacherDTO.getUserId() == null)
            return false;
        return true;
    }

    @Override
    public LessonTeacherDTO findByLessonTeacherId(int lessonTeacherId) {
        LessonTeacherEntity lessonTeacherEntity = lessonTeacherRepository.findById(lessonTeacherId);
        LessonTeacherDTO lessonTeacherDTO = new LessonTeacherDTO();
        BeanUtils.copyProperties(lessonTeacherEntity, lessonTeacherDTO);
        return lessonTeacherDTO;
    }

    @Override
    public void changeLessonTeacherStatus(ChangeLessonTeacherStatusDTO changeLessonTeacherStatusDTO) {
        LessonTeacherEntity lessonTeacherEntity = lessonTeacherRepository.findById(changeLessonTeacherStatusDTO.getLessonTeacherId());
        if (lessonTeacherEntity == null)
            throw new RuntimeException("Lesson teacher not found!.");

        lessonTeacherEntity.setEnabled(false);
        lessonTeacherRepository.saveAndFlush(lessonTeacherEntity);
    }

}
