package com.tesis.studyandlearn.repository;

import com.tesis.studyandlearn.model.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, Integer> {
}
