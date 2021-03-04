package com.tesis.studyandlearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonCommentDTO {
    private Integer id;
    private Integer userId;
    private Integer lessonId;
    private Date date;
    private String comment;
    private String userName;
    private String lessonName;

}
