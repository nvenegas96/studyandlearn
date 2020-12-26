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
@Table(name = "lesson_comment")
public class LessonCommentEntity {

    @Id
    @Column(name = "lesson_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "lesson_id")
    private Integer lessonId;

    @Column(name = "date")
    private Date date;

    @Column(name = "comment")
    private String comment;
}
