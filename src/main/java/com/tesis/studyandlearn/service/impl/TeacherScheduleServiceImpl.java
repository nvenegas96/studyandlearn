package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.LessonTeacherEntity;
import com.tesis.studyandlearn.model.TeacherScheduleEntity;
import com.tesis.studyandlearn.model.dto.ChangeTeacherScheduleStatusDTO;
import com.tesis.studyandlearn.model.dto.TeacherScheduleDTO;
import com.tesis.studyandlearn.repository.LessonRepository;
import com.tesis.studyandlearn.repository.LessonTeacherRepository;
import com.tesis.studyandlearn.repository.TeacherScheduleRepository;
import com.tesis.studyandlearn.repository.UserRepository;
import com.tesis.studyandlearn.service.TeacherScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TeacherScheduleServiceImpl implements TeacherScheduleService {

    @Autowired
    private TeacherScheduleRepository teacherScheduleRepository;


    @Autowired
    private LessonTeacherRepository lessonTeacherRepository;

    @Override
    public List<TeacherScheduleEntity> showAll(Integer userId) {
        List<LessonTeacherEntity> lessonTeacherEntitys = lessonTeacherRepository.findAllByUserId(userId);
        List<Integer> leassonTeacherIds = new ArrayList<>();
        for (LessonTeacherEntity lessonTeacherEntity : lessonTeacherEntitys) {
            leassonTeacherIds.add(lessonTeacherEntity.getId());

        }
        return teacherScheduleRepository.findAllByLessonTeacherIdIn(leassonTeacherIds);
    }

    @Override
    public List<TeacherScheduleDTO> showAllDTO(Integer userId) {
        List<TeacherScheduleEntity> lessonTeacherEntities = showAll(userId);
        List<TeacherScheduleDTO> teacherScheduleDTOS = new ArrayList<>();
        for (TeacherScheduleEntity teacherScheduleEntity : lessonTeacherEntities) {
            TeacherScheduleDTO teacherScheduleDTO = new TeacherScheduleDTO();
            BeanUtils.copyProperties(teacherScheduleEntity, teacherScheduleDTO);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            teacherScheduleDTO.setStartTime(sdf.format(teacherScheduleEntity.getStartTime()));
            teacherScheduleDTO.setEndTime(sdf.format(teacherScheduleEntity.getEndTime()));
            teacherScheduleDTOS.add(teacherScheduleDTO);
        }
        return teacherScheduleDTOS;
    }

    @Override
    public TeacherScheduleEntity findTeacherScheduleById(int teacherScheduleId) {
        TeacherScheduleEntity teacherScheduleEntity = teacherScheduleRepository.findById(teacherScheduleId);
        TeacherScheduleEntity teacherScheduleEntity1 = new TeacherScheduleEntity();
        BeanUtils.copyProperties(teacherScheduleEntity, teacherScheduleEntity1);
        return teacherScheduleEntity1;
    }

    @Override
    public TeacherScheduleDTO findTeacherDTOById(int teacherScheduleId) {
        TeacherScheduleEntity teacherScheduleEntity = findTeacherScheduleById(teacherScheduleId);
        TeacherScheduleDTO teacherScheduleDTO = new TeacherScheduleDTO();
        BeanUtils.copyProperties(teacherScheduleEntity, teacherScheduleDTO);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        teacherScheduleDTO.setStartTime(sdf.format(teacherScheduleEntity.getStartTime()));
        teacherScheduleDTO.setEndTime(sdf.format(teacherScheduleEntity.getEndTime()));
        return teacherScheduleDTO;
    }

    @Override
    public TeacherScheduleEntity createOrUpdateTeacherSchedule(TeacherScheduleDTO teacherScheduleDTO) {
        TeacherScheduleEntity teacherScheduleEntity = new TeacherScheduleEntity();
        BeanUtils.copyProperties(teacherScheduleDTO, teacherScheduleEntity);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            teacherScheduleEntity.setStartTime(
                    new Time(sdf.parse(teacherScheduleDTO.getStartTime()).getTime())
            );
            teacherScheduleEntity.setEndTime(
                    new Time(sdf.parse(teacherScheduleDTO.getEndTime()).getTime())
            );
        } catch (ParseException e) {
            log.error("Error al transformar las horas");
            return new TeacherScheduleEntity();
        }
        teacherScheduleRepository.saveAndFlush(teacherScheduleEntity);
        return teacherScheduleEntity;
    }

    @Override
    public boolean checkCorrectEntity(TeacherScheduleDTO teacherScheduleDTO) {
        if (teacherScheduleDTO.getStartTime() == null)
            return false;
        if (teacherScheduleDTO.getEndTime() == null)
            return false;
        if (teacherScheduleDTO.getDayOfTheWeek() == null)
            return false;
        return true;
    }

    @Override
    public void changeTeacherScheduleStatusDTO(ChangeTeacherScheduleStatusDTO changeTeacherScheduleStatusDTO) {
        TeacherScheduleEntity teacherScheduleEntity = teacherScheduleRepository.findById(changeTeacherScheduleStatusDTO.getTeacherScheduleId());
        if (teacherScheduleEntity == null)
            throw new RuntimeException("Teacher Schedule not found!.");

        teacherScheduleEntity.setEnabled(false);
        teacherScheduleRepository.saveAndFlush(teacherScheduleEntity);
    }


}
