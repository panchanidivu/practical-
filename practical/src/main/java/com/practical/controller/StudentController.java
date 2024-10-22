package com.practical.controller;

import org.hibernate.annotations.processing.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practical.dto.StudentCreateDTO;
import com.practical.dto.StudentDTO;
import com.practical.response.CustomResponseEntity;
import com.practical.response.CustomResponseStatus;
import com.practical.services.StudentService;
import com.practical.services.StudentServiceImpl;

import io.swagger.annotations.ApiParam;
import jakarta.validation.constraints.NotBlank;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student")
// @api(tags = "api")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity getAllStudent() {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(studentServiceImpl.getAllStudent()).build();
    }

     @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity addStudent( @ApiParam("studentDTO") @RequestBody  StudentCreateDTO studentDTO) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(studentServiceImpl.addStudent(studentDTO)).build();
    }

    @GetMapping(value = "/getByStudentId", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity getStudentById(@NotBlank(message="please enter studentID") @ApiParam("id")@RequestParam int id)  {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(studentServiceImpl.getStudentById(id)).build();
    }

     @PutMapping(value = "/updateStudent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity updateStudent(@ApiParam("studentDTO") @RequestBody  StudentDTO studentDTO) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(studentServiceImpl.updateStudent(studentDTO)).build();
    }

     @DeleteMapping(value = "/deleteByStudentId", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity deleteStudent( @RequestParam int id) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(studentServiceImpl.deleteStudent(id)).build();
    }
    
}
