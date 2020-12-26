package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonCategoryEntity;
import com.tesis.studyandlearn.model.UserEntity;
import com.tesis.studyandlearn.model.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository extends JpaRepository<UserTypeEntity, Integer> {

    List<UserTypeEntity> findAll();
}
