package com.base_student.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import com.base_student.dto.StudentDto;
import com.base_student.exception.BusinessException;

@Component
public class ValidationUtil {

    public void validate(StudentDto studentDto) throws BusinessException {
        if (Strings.isBlank(studentDto.getName())) {
            throw BusinessException.builder()
                    .message("El nombre no debe estar vacío")
                    .build();
        }
    }

}
