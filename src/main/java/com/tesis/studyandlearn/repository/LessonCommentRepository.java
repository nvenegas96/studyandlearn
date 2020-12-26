package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonCommentRepository extends JpaRepository<LessonCommentEntity, Integer> {
}
