package com.practical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practical.model.University;

public interface UniversityRepo extends JpaRepository<University,Integer> {
    
}
