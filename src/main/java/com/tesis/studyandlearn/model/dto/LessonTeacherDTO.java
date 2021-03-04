package com.tesis.studyandlearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonTeacherDTO {
    private Integer id;
    private Integer lessonId;
    private Integer userId;
    private String lessonName;
    private String userName;
    private Boolean enabled;
}
