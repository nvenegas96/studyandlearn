package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonCommentEntity;
import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.constans.Querys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LessonCommentRepository extends JpaRepository<LessonCommentEntity, Integer> {

   // LessonCommentEntity findByCommentId(int commentId);

    List<LessonCommentEntity> findAll();

}
