package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.LessonCategoryEntity;
import com.tesis.studyandlearn.model.LessonModalityEntity;
import com.tesis.studyandlearn.model.dto.ChangeLessonModalityStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeLessonStatusDTO;

import java.util.List;

public interface ModalityService {

    List<LessonModalityEntity> showAll();

    LessonModalityEntity findModalityById(int lessonModalityId);

    LessonModalityEntity createOrUpdateModality(LessonModalityEntity lessonModalityEntity);

    boolean checkCorrectEntity(LessonModalityEntity lessonModalityEntity);

    void changeLessonModalityStatus(ChangeLessonModalityStatusDTO changeLessonModalityStatusDTO);

}
