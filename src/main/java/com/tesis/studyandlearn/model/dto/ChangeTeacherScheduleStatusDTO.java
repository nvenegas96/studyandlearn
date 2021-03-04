package com.tesis.studyandlearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeTeacherScheduleStatusDTO {
    private int teacherScheduleId;
    private Boolean enabled;
}
