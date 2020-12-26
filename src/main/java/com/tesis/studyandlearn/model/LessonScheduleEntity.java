package com.tesis.studyandlearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lesson_schedule")
public class LessonScheduleEntity {

    @Id
    @Column(name = "lesson_schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lesson_id")
    private Integer lessonId;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "date")
    private Date date;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "assessment")
    private Integer assessment;
}
