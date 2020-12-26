package com.tesis.studyandlearn.model.dto;

import com.tesis.studyandlearn.model.constans.DayOfTheWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private Integer id;
    private Integer lessonTeacherId;
    private DayOfTheWeek dayOfTheWeek;
    private String startTime;
    private String endTime;
    private List<HourSegmentDTO> hours;
}
