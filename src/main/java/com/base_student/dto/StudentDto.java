package com.base_student.dto;

import com.base_student.model.StudentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.ObjectMapper;

/**
 * Representa el objeto de transferencia de datos (DTO) de un estudiante.
 * Se utiliza para transportar la información del estudiante entre capas de la aplicación,
 * especialmente en solicitudes y respuestas HTTP.
 *
 * @author base_student
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    /**
     * Identificador del estudiante.
     * Se ignora durante la serialización JSON para evitar exponerlo en entidades externas.
     */
    @JsonIgnore
    private Integer id;

    /**
     * Nombre del estudiante.
     */
    private String name;

    /**
     * Apellido del estudiante.
     */
    private String lastName;

    /**
     * Número telefónico del estudiante.
     */
    private String phone;

    /**
     * Correo electrónico del estudiante.
     */
    private String eMail;

    /**
     * Convierte esta representación DTO en un objeto de dominio {@link StudentModel}.
     *
     * @return instancia de {@link StudentModel} con los valores actuales del DTO
     */
    public StudentModel toModel() {
        return StudentModel.builder()
                .id(this.id)
                .name(this.name)
                .lastName(this.lastName)
                .phone(this.phone)
                .eMail(this.eMail)
                .build();
    }

    /**
     * Genera una representación JSON del objeto actual.
     *
     * @return cadena JSON equivalente al contenido del DTO
     */
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }

}
