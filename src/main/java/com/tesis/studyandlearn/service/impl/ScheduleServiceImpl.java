package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.*;
import com.tesis.studyandlearn.model.dto.*;
import com.tesis.studyandlearn.repository.LessonRepository;
import com.tesis.studyandlearn.repository.LessonScheduleRepository;
import com.tesis.studyandlearn.repository.LessonTeacherRepository;
import com.tesis.studyandlearn.repository.TeacherScheduleRepository;
import com.tesis.studyandlearn.service.ScheduleService;
import com.tesis.studyandlearn.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private LessonTeacherRepository lessonTeacherRepository;

    @Autowired
    private LessonScheduleRepository lessonScheduleRepository;

    @Autowired
    private TeacherScheduleRepository teacherScheduleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<LessonScheduleEntity> showAllSchedule() {
        return lessonScheduleRepository.findAll();
    }

    @Override
    public LessonScheduleEntity createOrUpdateSchedule(LessonScheduleDTO lessonScheduleDTO) {
        LessonScheduleEntity lessonScheduleEntity = new LessonScheduleEntity();
        BeanUtils.copyProperties(lessonScheduleDTO, lessonScheduleEntity);
        lessonScheduleRepository.saveAndFlush(lessonScheduleEntity);
        return lessonScheduleEntity;
    }

    @Override
    public boolean checkCorrectDTO(LessonScheduleDTO lessonScheduleDTO) {
        if (lessonScheduleDTO.getLessonId() == null)
            return false;
        if (lessonScheduleDTO.getStatusId() == null)
            return false;
        if (lessonScheduleDTO.getStudentId() == null)
            return false;
        return true;
    }

    @Override
    public LessonScheduleDTO findByScheduleId(int lessonScheduleId) {
        LessonScheduleEntity lessonScheduleEntity = lessonScheduleRepository.findById(lessonScheduleId);
        LessonScheduleDTO lessonScheduleDTO = new LessonScheduleDTO();
        BeanUtils.copyProperties(lessonScheduleEntity, lessonScheduleDTO);
        return lessonScheduleDTO;
    }

    private TeacherSchedule showScheduleByLeassonTeacher(int lessonId, int teacherId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        LessonTeacherEntity lessonTeacherEntity = lessonTeacherRepository.findByLessonIdAndUserId(
                lessonId,
                teacherId
        );
        List<TeacherScheduleEntity> teacherScheduleEntities = teacherScheduleRepository.findByLessonTeacherId(
                lessonTeacherEntity.getLessonId()
        );
        for (TeacherScheduleEntity teacherScheduleEntity : teacherScheduleEntities) {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(teacherScheduleEntity, scheduleDTO);
            scheduleDTO.setStartTime(teacherScheduleEntity.getStartTime().toString());
            scheduleDTO.setEndTime(teacherScheduleEntity.getEndTime().toString());
            scheduleDTO.setHours(
                    makeHourSegment(
                            teacherScheduleEntity,
                            lessonTeacherEntity.getLessonId()
                    )
            );
            scheduleDTOS.add(scheduleDTO);
        }
        TeacherSchedule teacherSchedule = new TeacherSchedule();
        teacherSchedule.setScheduleDTOS(scheduleDTOS);
        return teacherSchedule;
    }

    @Override
    public List<TeacherSchedule> showScheduleByLesson(int lessonId) {
        List<UserDTO> teachers =  userService.showAllUserByLessonId(lessonId);
        List<TeacherSchedule> teacherSchedules = new ArrayList<>();
        for (UserDTO teacher : teachers) {
            TeacherSchedule teacherSchedule = showScheduleByLeassonTeacher(
                    lessonId,
                    teacher.getId()
            );
            teacherSchedule.setTeacher(teacher);
            teacherSchedules.add(teacherSchedule);
        }
        return teacherSchedules;
    }

    private List<HourSegmentDTO> makeHourSegment(TeacherScheduleEntity scheduleDTO, Integer lessonId) {
        List<HourSegmentDTO> hourSegmentDTOS = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("HH:mm");
        LessonEntity lessonEntity = lessonRepository.findById(lessonId.intValue());
        Integer hourQuantity = lessonEntity.getHourQuantity();
        Time segment = scheduleDTO.getStartTime();
        do {
            HourSegmentDTO hourSegmentDTO = new HourSegmentDTO();
            hourSegmentDTO.setStartHour(format.format(segment));
            LocalTime localtime = segment.toLocalTime();
            localtime = localtime.plusHours(hourQuantity.longValue());
            hourSegmentDTO.setEndHour(localtime.toString());
            segment = Time.valueOf(localtime.now());
            hourSegmentDTOS.add(hourSegmentDTO);
        } while (segment.getTime() <= scheduleDTO.getEndTime().getTime());

        return hourSegmentDTOS;
    }
}
