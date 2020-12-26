package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.UserEntity;
import com.tesis.studyandlearn.model.dto.LessonDTO;
import com.tesis.studyandlearn.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserEntity> showAllUser();

    UserEntity createOrUpdateUser(UserDTO userDTO);

    boolean checkCorrectDTO(UserDTO userDTO);

    UserDTO findByUserId(int userId);

    List<UserDTO> showAllUserDTO();

    List<UserDTO> showAllUserByLessonId(int lessonId);


}
