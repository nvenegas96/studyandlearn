package com.tesis.studyandlearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserStatusDTO {
    private int userId;
    private int userTypeId;
    private int studyLevelId;
    private Boolean enabled;
}
