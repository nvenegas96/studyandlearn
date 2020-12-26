package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonStatusRepository extends JpaRepository<LessonStatusEntity, Integer> {
}
