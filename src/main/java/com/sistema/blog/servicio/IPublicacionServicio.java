package com.sistema.blog.servicio;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.entidades.Publicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPublicacionServicio {

    List<PublicacionDTO> obtenerTodasLasPulicaciones();

    PublicacionDTO obtenerPublicacionPorId(Long id);

    Page<PublicacionDTO> obtenerTodasLasPulicacionesPaginacion(int page, int size, String ordenadoPor, String sortDir);

    PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    PublicacionDTO actualizarPublicacionPorId(PublicacionDTO publicacionDTO, Long id);

    void borrarPublicacionPorId(Long id);
}
