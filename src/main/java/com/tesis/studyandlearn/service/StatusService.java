package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.LessonStatusEntity;
import com.tesis.studyandlearn.model.dto.ChangeLessonStatusesStatusDTO;

import java.util.List;

public interface StatusService {

    List<LessonStatusEntity> showAllStatus();

    LessonStatusEntity findStatusById(int lessonStatusId);

    LessonStatusEntity createOrUpdateStatus(LessonStatusEntity lessonStatusEntity);

    boolean checkCorrectEntity(LessonStatusEntity lessonStatusEntity);

    void changeLessonStatusesStatus(ChangeLessonStatusesStatusDTO changeLessonStatusesStatusDTO);
}
