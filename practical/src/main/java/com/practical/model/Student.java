package com.practical.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "student")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private Date dateOfBirth;
    private Long phoneNumber;
    
    private String enrollmentNumber;

    @ManyToOne(fetch = FetchType.LAZY)  // Many students belong to one university
    @JoinColumn(name = "university_id")
    private University university;


    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", enrollmentNumber='" + enrollmentNumber + '\'' +
                ", major='" + phoneNumber + '\'' +
                ", universityId=" + (university != null ? university.getUniversity_id() : null) + 
                '}';
    }

}
