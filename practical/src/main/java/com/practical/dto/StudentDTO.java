package com.practical.dto;

import java.util.Date;
import com.practical.model.University;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Integer id;

    private String name;

    private String address;

    private Date dateOfBirth;

    private Long phoneNumber;

    private String enrollmentNumber;

    private String universityName;

    private University university;

    @Override
    public String toString() {
        return "ClassName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber=" + phoneNumber +
                ", enrollmentNumber='" + enrollmentNumber + '\'' +
                ", universityName='" + universityName + '\'' +
                ", university=" + university +
                '}';
    }

}
