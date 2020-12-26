package com.tesis.studyandlearn.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_type")
public class UserTypeEntity {

    @Id
    @Column(name = "user_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "type_translation")
    private String typeTranslation;
}
