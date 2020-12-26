package com.tesis.studyandlearn.model;

import com.tesis.studyandlearn.model.constans.DayOfTheWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher_schedule")
public class TeacherScheduleEntity {

    @Id
    @Column(name = "teacher_schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "lesson_teacher_id")
    private Integer lessonTeacherId;


    @Column(name = "day_of_the_week")
    private DayOfTheWeek dayOfTheWeek;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

}
