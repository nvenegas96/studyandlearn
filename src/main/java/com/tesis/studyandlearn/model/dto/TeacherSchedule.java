package com.tesis.studyandlearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherSchedule {
    List<ScheduleDTO> scheduleDTOS;
    UserDTO teacher;
}
