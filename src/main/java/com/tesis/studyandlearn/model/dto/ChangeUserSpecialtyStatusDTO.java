package com.tesis.studyandlearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserSpecialtyStatusDTO {
    private int userSpecialtyId;
    private int userId;
    private int specialtyId;
    private Boolean enabled;
}
