package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.*;
import com.tesis.studyandlearn.model.dto.ChangeUserSpecialtyStatusDTO;
import com.tesis.studyandlearn.model.dto.LessonDTO;
import com.tesis.studyandlearn.model.dto.UserSpecialtyDTO;
import com.tesis.studyandlearn.repository.SpecialtyRepository;
import com.tesis.studyandlearn.repository.UserRepository;
import com.tesis.studyandlearn.repository.UserSpecialtyRepository;
import com.tesis.studyandlearn.service.UserSpecialtyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSpecialtyServiceImpl implements UserSpecialtyService {

    @Autowired
    private UserSpecialtyRepository userSpecialtyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public List<UserSpecialtyDTO> showAllUserSpecialtyDTO() {
        List<UserSpecialtyEntity> userSpecialtyEntities = userSpecialtyRepository.findAll();
        List<UserEntity> userEntities = userRepository.findAll();
        List<SpecialtyEntity> specialtyEntities = specialtyRepository.findAll();
        List<UserSpecialtyDTO> userSpecialtyDTOS = new ArrayList<>();
        for(UserSpecialtyEntity userSpecialtyEntity : userSpecialtyEntities){
            UserSpecialtyDTO userSpecialtyDTO = new UserSpecialtyDTO();
            BeanUtils.copyProperties(userSpecialtyEntity, userSpecialtyDTO);
            userSpecialtyDTO.setUserName(getUserName(userEntities, userSpecialtyDTO.getUserId()));
            userSpecialtyDTO.setSpecialtyName(getSpecialtyName(specialtyEntities, userSpecialtyDTO.getSpecialtyId()));
            userSpecialtyDTOS.add(userSpecialtyDTO);
        }
        return userSpecialtyDTOS;
    }

    private String getUserName(List<UserEntity> userEntities, int userId){
        for(UserEntity userEntity : userEntities){
            if(userEntity.getId() == userId)
                return userEntity.getName();
        }
        return null;
    }

    private String getSpecialtyName(List<SpecialtyEntity> specialtyEntities, int specialtyId){
        for(SpecialtyEntity specialtyEntity : specialtyEntities){
            if(specialtyEntity.getId() == specialtyId)
                return specialtyEntity.getName();
        }
        return null;
    }

    @Override
    public UserSpecialtyEntity createOrUpdateUserSpecialty(UserSpecialtyDTO userSpecialtyDTO) {
        UserSpecialtyEntity userSpecialtyEntity = new UserSpecialtyEntity();
        BeanUtils.copyProperties(userSpecialtyDTO, userSpecialtyEntity);
        userSpecialtyRepository.saveAndFlush(userSpecialtyEntity);
        return userSpecialtyEntity;
    }

    @Override
    public boolean checkCorrectDTO(UserSpecialtyDTO userSpecialtyDTO) {
        if (userSpecialtyDTO.getUserId() == null )
            return false;
        if (userSpecialtyDTO.getSpecialtyId() == null)
            return false;
        return true;
    }


    @Override
    public UserSpecialtyDTO findByUserSpecialtyId(int userSpecialtyId) {
        UserSpecialtyEntity userSpecialtyEntity = userSpecialtyRepository.findById(userSpecialtyId);
        UserSpecialtyDTO userSpecialtyDTO = new UserSpecialtyDTO();
        BeanUtils.copyProperties(userSpecialtyEntity, userSpecialtyDTO);
        return userSpecialtyDTO;
    }

    @Override
    public void changeUserSpecialtyStatus(ChangeUserSpecialtyStatusDTO changeUserSpecialtyDTO) {
        UserSpecialtyEntity userSpecialtyEntity = userSpecialtyRepository.findById(changeUserSpecialtyDTO.getUserSpecialtyId());
        if (userSpecialtyEntity == null)
            throw new RuntimeException("User specialty not found!.");

        userSpecialtyEntity.setEnabled(false);
        userSpecialtyRepository.saveAndFlush(userSpecialtyEntity);
    }

}
