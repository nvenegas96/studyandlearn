package com.tesis.studyandlearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonScheduleDTO {
    private Integer id;
    private Integer lessonId;
    private Integer studentId;
    private Integer teacherId;
    private Date date;
    private Integer statusId;
    private Integer assessment;
    private String lessonName;
    private String studentName;
    private String teacherName;
    private String statusName;

}
