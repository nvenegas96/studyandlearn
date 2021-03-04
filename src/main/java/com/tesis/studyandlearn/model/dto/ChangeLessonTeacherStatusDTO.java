package com.tesis.studyandlearn.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeLessonTeacherStatusDTO {
    private int lessonTeacherId;
    private int lessonId;
    private int userId;
    private Boolean enabled;
}
