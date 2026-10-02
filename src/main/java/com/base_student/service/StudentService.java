package com.base_student.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base_student.dto.StudentDto;
import com.base_student.dto.StudentRsDto;
import com.base_student.model.StudentModel;
import com.base_student.respository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(StudentModel::toDto).toList();
    }

    public StudentRsDto create(StudentDto dto) {
        Integer id = studentRepository.save(dto.toModel()).getId();
        return new StudentRsDto("Student added", id);
    }

}
