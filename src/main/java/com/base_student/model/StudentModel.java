package com.base_student.model;

import com.base_student.dto.StudentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", columnDefinition = "varchar(80)")
    private String name;
    @Column(name = "last_name", columnDefinition = "varchar(50)")
    private String lastName;
    @Column(name = "phone", columnDefinition = "varchar(20)")
    private String phone;
    @Column(name = "email", columnDefinition = "varchar(80)")
    private String eMail;

    public StudentDto toDto() {
        return StudentDto.builder()
                .id(this.id)
                .name(this.name)
                .lastName(this.lastName)
                .phone(this.phone)
                .eMail(this.eMail)
                .build();
    }

    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }

}
