package com.tesis.studyandlearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeLessonCategoryStatusDTO {
    private int lessonCategoryId;
    private Boolean enabled;
}
