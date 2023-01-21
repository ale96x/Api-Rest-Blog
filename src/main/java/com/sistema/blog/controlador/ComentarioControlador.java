package com.sistema.blog.controlador;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.servicio.impl.ComentarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicioImpl servicio;

    @PostMapping("/{id}")
    public ResponseEntity<ComentarioDTO> crearComentario(@RequestBody ComentarioDTO comentarioDTO, @PathVariable(name = "id") Long publicacionId){
        return new ResponseEntity<>(servicio.crearComentario(publicacionId,comentarioDTO), HttpStatus.CREATED);
    }
}
