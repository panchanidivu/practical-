package com.practical.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentCreateDTO {
    
    private String name;

    private String address;

    private Date dateOfBirth;
    
    private Long phoneNumber;

    private String enrollmentNumber;

    private Integer universitId;
}
