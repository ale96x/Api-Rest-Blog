package com.sistema.blog.servicio;

import com.sistema.blog.dto.ComentarioDTO;

public interface IComentarioServicio {
    ComentarioDTO crearComentario(Long publicacionId, ComentarioDTO comentarioDTO);
}
