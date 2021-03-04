package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.SpecialtyEntity;
import com.tesis.studyandlearn.model.StudyEntity;
import com.tesis.studyandlearn.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, Integer> {

    List<SpecialtyEntity> findAll();

    SpecialtyEntity findById(int specialtyId);


}
