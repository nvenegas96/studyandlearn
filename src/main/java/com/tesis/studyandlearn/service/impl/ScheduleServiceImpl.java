package com.tesis.studyandlearn.service.impl;

import com.tesis.studyandlearn.model.*;
import com.tesis.studyandlearn.model.dto.*;
import com.tesis.studyandlearn.repository.*;
import com.tesis.studyandlearn.service.EmailService;
import com.tesis.studyandlearn.service.ScheduleService;
import com.tesis.studyandlearn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
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
    private LessonStatusRepository lessonStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonModalityRepository lessonModalityRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

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
        List<TeacherScheduleEntity> teacherScheduleEntities = teacherScheduleRepository.findAllByLessonTeacherId(
                lessonTeacherEntity.getId()
        );
        for (TeacherScheduleEntity teacherScheduleEntity : teacherScheduleEntities) {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(teacherScheduleEntity, scheduleDTO);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            scheduleDTO.setStartTime(sdf.format(teacherScheduleEntity.getStartTime()));
            scheduleDTO.setEndTime(sdf.format(teacherScheduleEntity.getEndTime()));
            scheduleDTO.setHours(
                    makeWeeklySegment(
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
        List<UserDTO> teachers = userService.showAllUserByLessonId(lessonId);
        List<TeacherSchedule> teacherSchedules = new ArrayList<>();
        for (UserDTO teacher : teachers) {
            TeacherSchedule teacherSchedule = showScheduleByLeassonTeacher(
                    lessonId,
                    teacher.getId()
            );
            teacher.setAssessment(lessonScheduleRepository.showAssessmentByLessonAndTeacher(
                    lessonId,
                    teacher.getId()
            ));
            teacherSchedule.setTeacher(teacher);
            teacherSchedules.add(teacherSchedule);
        }
        return teacherSchedules;
    }

    @Override
    public boolean createReserve(int lessonId, int teacherId, String[] hours, Integer userId) {
        String pattern = "yyyy-MM-dd hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        List<Date> dates = new ArrayList<>();
        for (String hour : hours) {
            if (hour.isEmpty())
                continue;
            try {
                Date date = simpleDateFormat.parse(hour);
                dates.add(date);
            } catch (ParseException e) {
                log.error("Error to parse date: " + hour);
                return false;
            }
        }
        if (checkIfExitsReserve(lessonId, teacherId, dates)) {
            log.error("Fail to create new reserve, any date exits!");
            return false;
        }

        for (Date date : dates) {
            LessonScheduleEntity lessonScheduleEntity = new LessonScheduleEntity();
            lessonScheduleEntity.setLessonId(lessonId);
            lessonScheduleEntity.setTeacherId(teacherId);
            lessonScheduleEntity.setStudentId(userId);
            lessonScheduleEntity.setAssessment(0);
            lessonScheduleEntity.setStatusId(0);
            lessonScheduleEntity.setDate(date);
            lessonScheduleRepository.saveAndFlush(lessonScheduleEntity);
            log.info("Student id: " + userId + " create a new reserve to date: " + date.toString());
        }
        return true;
    }

    private boolean checkIfExitsReserve(int lessonId, int teacherId, List<Date> dates) {
        for (Date date : dates) {
            if (lessonScheduleRepository.findByLessonIdAndTeacherIdAndDate(
                    lessonId,
                    teacherId,
                    date
            ) != null) return true;
        }
        return false;
    }

    private List<HourSegmentDTO> makeWeeklySegment(TeacherScheduleEntity scheduleDTO, Integer lessonId) {
        List<HourSegmentDTO> hourSegmentDTOS = makeHourSegment(
                scheduleDTO,
                lessonId
        );
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusMonths(1);

        LocalDate week = startDate.withDayOfWeek(scheduleDTO.getDayOfTheWeek().ordinal() + 1);

        if (startDate.isAfter(week)) {
            startDate = week.plusWeeks(1); // start on next monday
        } else {
            startDate = week; // start on this monday
        }

        List<HourSegmentDTO> hourSegmentDTOTemplate = hourSegmentDTOS;

        hourSegmentDTOS = setDayToSegment(hourSegmentDTOS, startDate);

        while (startDate.isBefore(endDate)) {
            startDate = startDate.plusWeeks(1);
            hourSegmentDTOS.addAll(
                    setDayToSegment(hourSegmentDTOTemplate, startDate)
            );
        }
        hourSegmentDTOS = cleanReservedLessons(
                scheduleDTO.getLessonTeacherId(),
                hourSegmentDTOS
        );
        return hourSegmentDTOS;
    }

    private List<HourSegmentDTO> cleanReservedLessons(int lessonTeacherId, List<HourSegmentDTO> hourSegmentDTOS) {
        List<HourSegmentDTO> cleanReserved = new ArrayList<>();
        LessonTeacherEntity lessonTeacherEntity = lessonTeacherRepository.getOne(lessonTeacherId);
        String pattern = "yyyy-MM-dd hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        for (HourSegmentDTO hourSegmentDTO : hourSegmentDTOS) {
            Date date = null;
            try {
                date = simpleDateFormat.parse(hourSegmentDTO.getDate() + " " + hourSegmentDTO.getStartHour());
            } catch (ParseException e) {
                log.error("Error to parse date: " + hourSegmentDTO.getDate() + " " + hourSegmentDTO.getStartHour());
                return new ArrayList<>();
            }
            List<Date> dates = new ArrayList<>();
            dates.add(date);
            boolean response = checkIfExitsReserve(
                    lessonTeacherEntity.getLessonId(),
                    lessonTeacherEntity.getUserId(),
                    dates
            );
            if (!response)
                cleanReserved.add(hourSegmentDTO);

        }
        return cleanReserved;
    }

    private List<HourSegmentDTO> setDayToSegment(List<HourSegmentDTO> hourSegmentDTOS, LocalDate date) {
        List<HourSegmentDTO> hourSegmentDTOList = new ArrayList<>();
        for (HourSegmentDTO hourSegmentDTO : hourSegmentDTOS) {
            HourSegmentDTO hourSegmentDTONew = new HourSegmentDTO();
            BeanUtils.copyProperties(hourSegmentDTO, hourSegmentDTONew);
            hourSegmentDTONew.setDate(date.toString());
            hourSegmentDTOList.add(hourSegmentDTONew);
        }
        return hourSegmentDTOList;
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
            segment = Time.valueOf(localtime);
            hourSegmentDTOS.add(hourSegmentDTO);
        } while (segment.getTime() < scheduleDTO.getEndTime().getTime());

        return hourSegmentDTOS;
    }

    @Override
    public List<LessonScheduleDTO> showAllLessonScheduleDTOByTeacherEmail() {
        List<LessonScheduleEntity> lessonScheduleEntities = lessonScheduleRepository.findAll();
        return showAllLessonScheduleDTOByData(lessonScheduleEntities);
    }

    @Override
    public List<LessonScheduleDTO> showAllLessonScheduleDTOByTeacherEmail(String userEmail) {
        UserEntity userEntity = userRepository.findByEmail(userEmail);
        List<LessonScheduleEntity> lessonScheduleEntities = lessonScheduleRepository.findAllByTeacherId(userEntity.getId());
        return showAllLessonScheduleDTOByData(lessonScheduleEntities);
    }

    @Override
    public List<LessonScheduleDTO> showAllLessonScheduleDTOByStudentEmail(String userEmail) {
        UserEntity userEntity = userRepository.findByEmail(userEmail);
        List<LessonScheduleEntity> lessonScheduleEntities = lessonScheduleRepository.findAllByStudentId(userEntity.getId());
        return showAllLessonScheduleDTOByData(lessonScheduleEntities);
    }

    private List<LessonScheduleDTO> showAllLessonScheduleDTOByData(List<LessonScheduleEntity> lessonScheduleEntities) {
        List<LessonEntity> lessonEntities = lessonRepository.findAll();
        List<LessonStatusEntity> lessonStatusEntities = lessonStatusRepository.findAll();
        List<LessonModalityEntity> modalityEntities = lessonModalityRepository.findAll();
        List<LessonScheduleDTO> lessonScheduleDTOS = new ArrayList<>();
        for (LessonScheduleEntity lessonScheduleEntity : lessonScheduleEntities) {
            LessonScheduleDTO lessonScheduleDTO = new LessonScheduleDTO();
            BeanUtils.copyProperties(lessonScheduleEntity, lessonScheduleDTO);
            lessonScheduleDTO.setLessonName(getLessonName(lessonEntities, lessonScheduleDTO.getLessonId()));
            lessonScheduleDTO.setStatusName(getStatusName(lessonStatusEntities, lessonScheduleDTO.getStatusId()));
            lessonScheduleDTO.setStudentName(getUserName(lessonScheduleEntity.getStudentId()));
            lessonScheduleDTO.setTeacherName(getUserName(lessonScheduleEntity.getTeacherId()));
            lessonScheduleDTO.setModalityName(getModalityName(modalityEntities, lessonEntities, lessonScheduleEntity));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            lessonScheduleDTO.setDate(sdf.format(lessonScheduleEntity.getDate()));
            lessonScheduleDTOS.add(lessonScheduleDTO);
        }
        return lessonScheduleDTOS;
    }

    @Override
    public void changeScheduleStatus(ChangeScheduleStatusDTO changeScheduleStatusDTO) {
        LessonScheduleEntity lessonScheduleEntity = lessonScheduleRepository.findById(changeScheduleStatusDTO.getLessonScheduleId());
        if (lessonScheduleEntity == null)
            throw new RuntimeException("Lesson Schedule not found!.");
        LessonStatusEntity lessonStatusEntity = lessonStatusRepository.findById(changeScheduleStatusDTO.getScheduleStatusId());
        if (lessonStatusEntity == null)
            throw new RuntimeException("Lesson Status not found!.");

        lessonScheduleEntity.setStatusId(lessonStatusEntity.getId());
        lessonScheduleRepository.saveAndFlush(lessonScheduleEntity);

        try {
            emailService.changeScheduleStatusEmail(
                    lessonScheduleEntity.getStudentId(),
                    lessonStatusEntity.getName(),
                    lessonScheduleEntity.getDate().toString()
            );
            emailService.changeScheduleStatusEmail(
                    lessonScheduleEntity.getTeacherId(),
                    lessonStatusEntity.getName(),
                    lessonScheduleEntity.getDate().toString()
            );
        } catch (Exception e) {
            log.error("Error to send email ", e);
        }

    }


    private String getModalityName(
            List<LessonModalityEntity> modalityEntities,
            List<LessonEntity> lessonEntities,
            LessonScheduleEntity lessonScheduleEntity
    ) {
        for (LessonEntity lessonEntity : lessonEntities) {
            if (lessonEntity.getId().equals(lessonScheduleEntity.getLessonId())) {
                for (LessonModalityEntity modalityEntity : modalityEntities) {
                    if (lessonEntity.getModalityId().equals(modalityEntity.getId()))
                        return modalityEntity.getName();
                }
            }
        }
        return "";
    }

    private String getUserName(Integer userId) {
        return userRepository.findById(userId.intValue()).getName();
    }

    private String getLessonName(List<LessonEntity> lessonEntities, int lessonId) {
        for (LessonEntity lessonEntity : lessonEntities) {
            if (lessonEntity.getId() == lessonId)
                return lessonEntity.getName();
        }
        return null;
    }

    private String getStatusName(List<LessonStatusEntity> lessonStatusEntities, int statusId) {
        for (LessonStatusEntity lessonStatusEntity : lessonStatusEntities) {
            if (lessonStatusEntity.getId() == statusId)
                return lessonStatusEntity.getName();
        }
        return null;
    }

    @Override
    public void changeAssessment(ChangeLessonScheduleAssessmentDTO changeLessonScheduleAssessmentDTO) {
        LessonScheduleEntity lessonScheduleEntity = lessonScheduleRepository.findById(changeLessonScheduleAssessmentDTO.getLessonScheduleId());
        if (lessonScheduleEntity == null)
            throw new RuntimeException("Lesson Schedule not found!.");

        lessonScheduleEntity.setAssessment(changeLessonScheduleAssessmentDTO.getAssessment());
        lessonScheduleRepository.saveAndFlush(lessonScheduleEntity);

    }

}
