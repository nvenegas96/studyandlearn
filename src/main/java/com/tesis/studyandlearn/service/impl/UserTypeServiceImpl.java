package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.UserTypeEntity;
import com.tesis.studyandlearn.repository.UserTypeRepository;
import com.tesis.studyandlearn.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public List<UserTypeEntity> showAll() {
        return userTypeRepository.findAll();
    }
}
