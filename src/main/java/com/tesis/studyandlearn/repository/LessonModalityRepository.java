package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonModalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonModalityRepository extends JpaRepository<LessonModalityEntity, Integer> {
}
