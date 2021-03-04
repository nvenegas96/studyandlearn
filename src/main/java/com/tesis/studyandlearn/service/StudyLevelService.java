package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.LessonModalityEntity;
import com.tesis.studyandlearn.model.SpecialtyEntity;
import com.tesis.studyandlearn.model.StudyEntity;
import com.tesis.studyandlearn.model.dto.ChangeLessonModalityStatusDTO;
import com.tesis.studyandlearn.model.dto.ChangeStudyStatusDTO;

import java.util.List;

public interface StudyLevelService {

    List<StudyEntity> showAll();

    StudyEntity createOrUpdateStudy(StudyEntity studyEntity);

    boolean checkCorrectEntity(StudyEntity studyEntity);

    StudyEntity findStudyById(int studyId);

    void changeStudyStatus(ChangeStudyStatusDTO changeStudyStatusDTO);
}
