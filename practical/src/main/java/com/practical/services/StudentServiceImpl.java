package com.practical.services;

import java.util.List;

import com.practical.dto.StudentCreateDTO;
import com.practical.dto.StudentDTO;


public interface StudentServiceImpl  {

    List<StudentDTO> getAllStudent();

    StudentCreateDTO addStudent(StudentCreateDTO studentDTO);

    StudentDTO getStudentById(Integer id);

    StudentDTO updateStudent(StudentDTO studentDTO);

    boolean deleteStudent( Integer id);
    
}
