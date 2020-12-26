package com.tesis.studyandlearn.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private Integer hourQuantity;
    private Integer modalityId;
    private Integer categoryId;
    private String modalityName;
    private String categoryName;
}
