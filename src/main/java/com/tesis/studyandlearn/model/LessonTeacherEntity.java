package com.tesis.studyandlearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lesson_teacher")
public class LessonTeacherEntity {

    @Id
    @Column(name = "lesson_teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lesson_id")
    private Integer lessonId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "enabled")
    private boolean enabled;
}
