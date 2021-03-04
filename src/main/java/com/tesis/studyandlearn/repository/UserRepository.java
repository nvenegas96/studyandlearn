package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.LessonCategoryEntity;
import com.tesis.studyandlearn.model.LessonEntity;
import com.tesis.studyandlearn.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findById(int userId);

    List<UserEntity> findAll();

    UserEntity findByEmail(String email);

}
