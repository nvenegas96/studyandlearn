package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity, Integer> {

    StudyEntity findById(int studyId);

    List<StudyEntity> findAll();
}
