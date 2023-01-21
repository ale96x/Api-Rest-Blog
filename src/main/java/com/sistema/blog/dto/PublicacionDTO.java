package com.sistema.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicacionDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String contenido;
}
