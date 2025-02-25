package com.tesis.studyandlearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HourSegmentDTO {
    private String startHour;
    private String endHour;
    private String date;
}
