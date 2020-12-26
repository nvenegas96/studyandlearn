package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonCategoryRepository extends JpaRepository<LessonCategoryEntity, Integer> {

    List<LessonCategoryEntity> findAllByEnable(boolean isEnable);

    LessonCategoryEntity findById(int categoryId);

}
