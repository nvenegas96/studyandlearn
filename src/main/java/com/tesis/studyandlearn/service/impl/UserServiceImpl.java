package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.*;
import com.tesis.studyandlearn.model.dto.UserDTO;
import com.tesis.studyandlearn.repository.*;
import com.tesis.studyandlearn.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private LessonTeacherRepository lessonTeacherRepository;

    @Autowired
    private UserSpecialtyRepository userSpecialtyRepository;


    @Override
    public List<UserEntity> showAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<UserDTO> showAllUserDTO() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserTypeEntity> userTypeEntities = userTypeRepository.findAll();
        List<StudyEntity> studyEntities = studyRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
            userDTO.setUserTypeTranslation(getTypeTranslation(userTypeEntities, userDTO.getUserTypeId()));
            userDTO.setStudyName(findStudyName(studyEntities, userDTO.getStudyLevelId()));
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public List<UserDTO> showAllUserByLessonId(int lessonId) {
        List<LessonTeacherEntity> lessonTeacherEntities = lessonTeacherRepository.findAllByLessonId(lessonId);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (LessonTeacherEntity lessonTeacherEntity : lessonTeacherEntities) {
            UserEntity userEntity = userRepository.findById(lessonTeacherEntity.getUserId().intValue());
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
            userDTO.setSpecialtys(getSpecialtyNames(userDTO.getId()));
            userDTO.setStudyName(getStudyName(userDTO.getId()));
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    private String getStudyName(Integer userId) {
        return studyRepository.findById(userId.intValue()).getName();
    }

    private List<String> getSpecialtyNames(Integer userId) {
        return userSpecialtyRepository.getAllSpecialtyNameByUserId(userId);
    }

    private String getTypeTranslation(List<UserTypeEntity> userTypeEntities, int userTypeId) {
        for (UserTypeEntity userTypeEntity : userTypeEntities) {
            if (userTypeEntity.getId() == userTypeId)
                return userTypeEntity.getTypeTranslation();
        }
        return null;
    }

    private String findStudyName(List<StudyEntity> studyEntities, int studyId) {
        for (StudyEntity studyEntity : studyEntities) {
            if (studyEntity.getId() == studyId)
                return studyEntity.getName();
        }
        return null;
    }

    @Override
    public UserEntity createOrUpdateUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        userRepository.saveAndFlush(userEntity);
        return userEntity;
    }

    @Override
    public boolean checkCorrectDTO(UserDTO userDTO) {
        if (userDTO.getName() == null || userDTO.getName().isEmpty())
            return false;
        if (userDTO.getUserTypeId() == null)
            return false;
        if (userDTO.getStudyLevelId() == null)
            return false;
        return true;
    }

    @Override
    public UserDTO findByUserId(int userId) {
        UserEntity userEntity = userRepository.findById(userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDTO);
        return userDTO;
    }

}
