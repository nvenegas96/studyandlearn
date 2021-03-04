package com.tesis.studyandlearn.service;

import com.tesis.studyandlearn.model.TeacherScheduleEntity;
import com.tesis.studyandlearn.model.dto.ChangeTeacherScheduleStatusDTO;
import com.tesis.studyandlearn.model.dto.TeacherScheduleDTO;

import java.util.List;

public interface TeacherScheduleService {

    List<TeacherScheduleEntity> showAll(Integer userId);

    List<TeacherScheduleDTO> showAllDTO(Integer userId);

    TeacherScheduleEntity findTeacherScheduleById(int teacherScheduleEntity);

    TeacherScheduleDTO findTeacherDTOById(int teacherScheduleId);

    TeacherScheduleEntity createOrUpdateTeacherSchedule(TeacherScheduleDTO teacherScheduleDTO);

    boolean checkCorrectEntity(TeacherScheduleDTO teacherScheduleDTO);

    void changeTeacherScheduleStatusDTO(ChangeTeacherScheduleStatusDTO changeTeacherScheduleStatusDTO);


}
