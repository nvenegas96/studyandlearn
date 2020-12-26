package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.UserSpecialtyEntity;
import com.tesis.studyandlearn.model.constans.Querys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSpecialtyRepository extends JpaRepository<UserSpecialtyEntity, Integer> {

    @Query(value = Querys.specialtyNameQuery, nativeQuery = true)
    List<String> getAllSpecialtyNameByUserId(int userId);
}
