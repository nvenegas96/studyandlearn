package com.tesis.studyandlearn.model.dto;

import com.tesis.studyandlearn.model.constans.DayOfTheWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherScheduleDTO {
    private Integer id;
    private Integer lessonTeacherId;
    private DayOfTheWeek dayOfTheWeek;
    private String startTime;
    private String endTime;
    private boolean enabled;

}
