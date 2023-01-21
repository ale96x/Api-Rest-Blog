package com.sistema.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {
    private Long id;
    private String cuerpo;
    private String nombre;
    private String email;
}
