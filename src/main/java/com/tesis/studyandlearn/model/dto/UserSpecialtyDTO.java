package com.tesis.studyandlearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSpecialtyDTO {
    private Integer id;
    private Integer userId;
    private Integer specialtyId;
    private String userName;
    private String specialtyName;
    private Boolean enabled;

}
