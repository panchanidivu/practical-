package com.practical.dto;

import java.util.List;

import com.practical.model.Student;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UniversityDTO {


    private int id;

    private String name;

    private String location;

    private Long phoneNumber;

    private List<Student> students;
    
}
