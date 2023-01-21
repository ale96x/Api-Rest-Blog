package com.sistema.blog.servicio.impl;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.entidades.Comentario;
import com.sistema.blog.entidades.Publicacion;
import com.sistema.blog.excepciones.ResourceNotFoundException;
import com.sistema.blog.repositorio.ComentarioRepositorio;
import com.sistema.blog.repositorio.PublicacionRepositorio;
import com.sistema.blog.servicio.IComentarioServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServicioImpl implements IComentarioServicio {

    @Autowired
    @Qualifier("comentarioMapper")
    private ModelMapper mapper;

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;


    @Override
    public ComentarioDTO crearComentario(Long publicacionId, ComentarioDTO comentarioDTO) {
        Comentario comentario = mapper.map(comentarioDTO, Comentario.class); //Se crea una entidad comentario a partir del comentarioDTO obtenido por parametro
        Publicacion publicacion = publicacionRepositorio.
                findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion","id",publicacionId)); //Se obtiene la publicacion desde la base de datos
        comentario.setPublicacion(publicacion);  //Se establece la publicacion existente en el campo de la clase Comentario
        return mapper.map(comentarioRepositorio.save(comentario),ComentarioDTO.class); //Se guarda el comentario
    }
}
