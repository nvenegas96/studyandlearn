package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.UserSpecialtyEntity;
import com.tesis.studyandlearn.model.dto.ChangeUserSpecialtyStatusDTO;
import com.tesis.studyandlearn.model.dto.LessonDTO;
import com.tesis.studyandlearn.model.dto.UserSpecialtyDTO;

import java.util.List;

public interface UserSpecialtyService {

    List<UserSpecialtyDTO> showAllUserSpecialtyDTO();

    UserSpecialtyEntity createOrUpdateUserSpecialty(UserSpecialtyDTO userSpecialtyDTO);

    boolean checkCorrectDTO(UserSpecialtyDTO userSpecialtyDTO);

    UserSpecialtyDTO findByUserSpecialtyId(int userSpecialtyId);

    void changeUserSpecialtyStatus(ChangeUserSpecialtyStatusDTO changeUserSpecialtyDTO);
}
