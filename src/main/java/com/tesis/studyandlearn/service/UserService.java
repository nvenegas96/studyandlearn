package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.UserEntity;
import com.tesis.studyandlearn.model.dto.*;

import java.util.List;

public interface UserService {

    List<UserEntity> showAllUser();

    UserEntity createOrUpdateUser(UserDTO userDTO);

    boolean checkCorrectDTO(UserDTO userDTO);

    UserDTO findByUserId(int userId);

    UserEntity findByEmailId(String email);

    UserDTO findDTOByEmailId(String email);

    List<UserDTO> showAllUserDTO();

    List<UserDTO> showAllUserByLessonId(int lessonId);
    
    boolean validNewUser(UserDTO userDTO);

    void createNewUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO, String email);

    void changeUserStatus(ChangeUserStatusDTO changeUserStatusDTO);

}
