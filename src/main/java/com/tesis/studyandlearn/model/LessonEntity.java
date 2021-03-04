package com.tesis.studyandlearn.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lesson")
public class LessonEntity {

    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "hour_quantity")
    private Integer hourQuantity;

    @Column(name = "modality_id")
    private Integer modalityId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "enabled")
    private boolean enabled;
}

