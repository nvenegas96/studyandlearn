package com.tesis.studyandlearn.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_specialty")
public class UserSpecialtyEntity {

    @Id
    @Column(name = "user_specialty_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "specialty_id")
    private Integer specialtyId;

    @Column(name = "enabled")
    private boolean enabled;

}
