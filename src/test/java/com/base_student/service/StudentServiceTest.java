package com.base_student.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.base_student.dto.StudentRequestDto;
import com.base_student.dto.StudentResponseDto;
import com.base_student.exception.BusinessException;
import com.base_student.model.StudentModel;
import com.base_student.respository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    @DisplayName("Should Return Exeption By Email")
    void shouldReturnExeptionByEmail() {

        StudentRequestDto studentRequestDto = new StudentRequestDto(1, "John", "Doe", "+52 123 11 22 33",
                "j.doe@email.com");
        StudentModel studentModel = studentRequestDto.toModel();
        when(studentRepository.existsByEmail(studentModel.getEmail())).thenReturn(true);

        assertThrows(BusinessException.class, () -> studentService.create(studentRequestDto));

    }

    @Test
    void testDeleteById() {
        Integer id = 1;
        doNothing().when(studentRepository).deleteById(id);
        studentService.deleteById(id);
        verify(studentRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("should Return Student List")
    void shouldReturnStudentList() {
        List<StudentModel> studentModelList = List.of(
                new StudentModel(1, "Floor", "Jansen", "+52 123 11 22 33", "f.jansen@gmail.com"));
        when(studentRepository.findAll()).thenReturn(studentModelList);
        List<StudentResponseDto> responseDtos = studentService.findAll();
        assertThat(responseDtos).hasSize(1);
    }

    @Test
    @DisplayName("Should return a student by id")
    void shouldReturnStudentById() {
        Integer id = 1;
        StudentModel studentModel = new StudentModel(1, "John", "Doe", "+52 123 11 22 33", "j.doe@mail.com");
        when(studentRepository.findById(id)).thenReturn(Optional.of(studentModel));
        StudentResponseDto studentResponseDto = studentService.findById(id);
        assertEquals(studentModel.toDto(), studentResponseDto);
    }
}
