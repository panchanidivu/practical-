package com.practical.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practical.dto.StudentCreateDTO;
import com.practical.dto.UniversityDTO;
import com.practical.response.CustomResponseEntity;
import com.practical.response.CustomResponseStatus;
import com.practical.services.UniversityService;

import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/uni")
public class UniversityController {

    @Autowired
    UniversityService universityService;

     @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity addUniversity( @ApiParam("uni") @RequestBody  UniversityDTO universityDTO) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(universityService.addUniversity(universityDTO)).build();
    }

    
    
}
