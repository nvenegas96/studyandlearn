package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.*;
import com.tesis.studyandlearn.model.dto.LessonCommentDTO;
import com.tesis.studyandlearn.repository.LessonCommentRepository;
import com.tesis.studyandlearn.repository.LessonRepository;
import com.tesis.studyandlearn.repository.UserRepository;
import com.tesis.studyandlearn.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private LessonCommentRepository lessonCommentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public List<LessonCommentDTO> showAllCommentDTO() {
        List<LessonCommentEntity> lessonCommentEntities = lessonCommentRepository.findAll();
        List<UserEntity> userEntities = userRepository.findAll();
        List<LessonEntity> lessonEntities = lessonRepository.findAll();
        List<LessonCommentDTO> lessonCommentDTOS = new ArrayList<>();
        for(LessonCommentEntity lessonCommentEntity : lessonCommentEntities){
            LessonCommentDTO lessonCommentDTO = new LessonCommentDTO();
            BeanUtils.copyProperties(lessonCommentEntity, lessonCommentDTO);
            lessonCommentDTO.setUserName(getUserName(userEntities, lessonCommentDTO.getUserId()));
            lessonCommentDTO.setLessonName(getLessonName(lessonEntities, lessonCommentDTO.getLessonId()));
            lessonCommentDTOS.add(lessonCommentDTO);
        }
        return lessonCommentDTOS;
    }

    private String getUserName(List<UserEntity> userEntities, int userId){
        for(UserEntity userEntity : userEntities){
            if(userEntity.getId() == userId)
                return userEntity.getName();
        }
        return null;
    }

    private String getLessonName(List<LessonEntity> lessonEntities, int lessonId){
        for(LessonEntity lessonEntity : lessonEntities){
            if(lessonEntity.getId() == lessonId)
                return lessonEntity.getName();
        }
        return null;
    }


    @Override
    public LessonCommentEntity createOrUpdateComment(LessonCommentEntity lessonCommentEntity, String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        lessonCommentEntity.setUserId(userEntity.getId());
        lessonCommentEntity.setDate(new Date());
        lessonCommentRepository.saveAndFlush(lessonCommentEntity);
        return lessonCommentEntity;
    }

    @Override
    public boolean checkCorrectDTO(LessonCommentEntity lessonCommentEntity) {

        return true;
    }
/*
    @Override
    public LessonCommentDTO findByCommentId(int commentId) {
        LessonCommentEntity lessonCommentEntity = lessonCommentRepository.findByCommentId(commentId);
        LessonCommentDTO lessonCommentDTO = new LessonCommentDTO();
        BeanUtils.copyProperties(lessonCommentEntity, lessonCommentDTO);
        return lessonCommentDTO;
    }
*/
}
