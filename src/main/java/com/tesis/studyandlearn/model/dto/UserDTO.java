package com.tesis.studyandlearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Boolean enabled;
    private String address;
    private String phone;
    private Integer userTypeId;
    private Integer studyLevelId;
    private String userTypeTranslation;
    private String studyName;
    private List<String> specialtys;
    private Integer assessment;
}
