package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.*;
import com.tesis.studyandlearn.model.dto.*;

import java.util.List;

public interface SpecialtyService {

    List<SpecialtyEntity> showAllSpecialty();


    SpecialtyEntity createOrUpdateSpecialty(SpecialtyEntity specialtyEntity);

    boolean checkCorrectEntity(SpecialtyEntity specialtyEntity);

    SpecialtyEntity findSpecialtyById(int specialtyId);

    void changeSpecialtyStatus(ChangeSpecialtyStatusDTO changeSpecialtyStatusDTO);


}
