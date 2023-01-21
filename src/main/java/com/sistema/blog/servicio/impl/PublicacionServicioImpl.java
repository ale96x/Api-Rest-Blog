package com.sistema.blog.servicio.impl;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.entidades.Publicacion;
import com.sistema.blog.excepciones.ResourceNotFoundException;
import com.sistema.blog.repositorio.PublicacionRepositorio;
import com.sistema.blog.servicio.IPublicacionServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServicioImpl implements IPublicacionServicio {

    @Autowired
    @Qualifier("publicacionMapper")
    private ModelMapper mapper;

    @Autowired
    private PublicacionRepositorio repositorio;

    @Override
    public List<PublicacionDTO> obtenerTodasLasPulicaciones() {
        List<PublicacionDTO> list = repositorio.findAll().stream()
                .map(pub -> mapper.map(pub,PublicacionDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public PublicacionDTO obtenerPublicacionPorId(Long id) {
        Publicacion publicacion = repositorio.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion","id",id)); //Si no encuentra el registro devuelve una exception de tipo ResourceNotFoundException
        return mapper.map(publicacion,PublicacionDTO.class);
    }

    @Override
    public Page<PublicacionDTO> obtenerTodasLasPulicacionesPaginacion(int page, int size, String ordenadoPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenadoPor).ascending() : Sort.by(ordenadoPor).descending();
        PageRequest pageRequest = PageRequest.of(page,size, sort);
        Page<PublicacionDTO> pageDTO =  repositorio.findAll(pageRequest).map(pub -> mapper.map(pub,PublicacionDTO.class));
        return pageDTO;
    }

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = repositorio.save(mapper.map(publicacionDTO,Publicacion.class)); //toEntity(publicacionDTO)
        return mapper.map(publicacion,PublicacionDTO.class); //toDTO(publicacion)
    }

    @Override
    public PublicacionDTO actualizarPublicacionPorId(PublicacionDTO publicacionDTO, Long id) {
        //Se debe verificar primero si se encuentra el id
        Publicacion publicacion = repositorio.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion","id",id)); //Se obtiene la publicacion
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        Publicacion publicacionActualizada = repositorio.save(publicacion); //Se realiza la actualizacion en la base de datos
        PublicacionDTO dto = mapper.map(publicacionActualizada,PublicacionDTO.class); //Se mapea la publicacion actualizada a DTO
        return dto;
    }

    @Override
    public void borrarPublicacionPorId(Long id) {
        //Se debe verificar primero si se encuentra el id
        Publicacion publicacion = repositorio.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion","id",id)); //Se obtiene la publicacion
        repositorio.deleteById(publicacion.getId());
    }
}
