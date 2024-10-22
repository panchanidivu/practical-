package com.practical.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practical.dto.UniversityDTO;
import com.practical.model.University;
import com.practical.repository.UniversityRepo;

@Service
public class UniversityService {

    @Autowired
    UniversityRepo universityRepo;

    public UniversityDTO addUniversity(UniversityDTO universityDTO) {
        University uni= new University();
        uni.setName(universityDTO.getName());
        uni.setLocation(universityDTO.getLocation());
        uni.setPhoneNumber(universityDTO.getPhoneNumber());
        universityRepo.save(uni);
        return universityDTO;
    }

    
}
