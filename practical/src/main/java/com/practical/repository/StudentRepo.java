package com.practical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practical.model.Student;

public interface StudentRepo extends JpaRepository<Student,Integer> {

}
