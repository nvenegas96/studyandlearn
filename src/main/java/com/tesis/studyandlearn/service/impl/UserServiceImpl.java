package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.*;
import com.tesis.studyandlearn.model.dto.ChangeLessonStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeUserStatusDTO;
import com.tesis.studyandlearn.model.dto.LessonScheduleDTO;
import com.tesis.studyandlearn.model.dto.UserDTO;
import com.tesis.studyandlearn.repository.*;
import com.tesis.studyandlearn.service.EmailService;
import com.tesis.studyandlearn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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

    @Autowired
    private EmailService emailService;


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
            userDTO.setStudyName(getStudyName(userDTO.getStudyLevelId()));
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public boolean validNewUser(UserDTO userDTO) {
        // usuario no exista en la base de datos
        if (userRepository.findByEmail(userDTO.getEmail()) != null)
            return false;
        // datos correctos
        return true;
    }

    @Override
    public void createNewUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userDTO.setEnabled(true);
        BeanUtils.copyProperties(userDTO, userEntity);
        userEntity.setStudyLevelId(0); // por defecto NO APLICA
        userEntity.setUserTypeId(0); // por defecto STUDENT
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.saveAndFlush(userEntity);

        try {
            emailService.welcomeEmail(userEntity.getId());
        } catch (Exception e) {
            log.error("Error when try to send welcome email", e);
        }
    }

    @Override
    public void updateUser(UserDTO userDTO, String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (!userDTO.getPassword().isEmpty()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        if (userDTO.getName().isEmpty() || userDTO.getAddress().isEmpty() || userDTO.getPhone().isEmpty())
            throw new RuntimeException("All fields are requiered!");
        userEntity.setName(userDTO.getName());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setPhone(userDTO.getPhone());
        userRepository.saveAndFlush(userEntity);
        emailService.changeUserProfile(userEntity.getId());
    }

    private String getStudyName(Integer studyId) {
        StudyEntity studyEntity = studyRepository.findById(studyId.intValue());
        if(studyEntity != null)
            return studyEntity.getName();
        return "";
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

    @Override
    public UserEntity findByEmailId(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDTO findDTOByEmailId(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDTO);
        userDTO.setPassword("");
        return userDTO;
    }

    @Override
    public void changeUserStatus(ChangeUserStatusDTO changeUserStatusDTO) {
        UserEntity userEntity = userRepository.findById(changeUserStatusDTO.getUserId());
        if (userEntity == null)
            throw new RuntimeException("User not found!.");

        userEntity.setEnabled(false);
        userRepository.saveAndFlush(userEntity);
    }

}
