package com.base_student.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base_student.dto.StudentDto;
import com.base_student.dto.StudentRsDto;
import com.base_student.service.StudentService;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<StudentDto> studentDtos = studentService.findAll();
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody StudentDto studentDto) {
        StudentRsDto rsDto = studentService.create(studentDto);
        return new ResponseEntity<>(rsDto, HttpStatus.OK);
    }

}
