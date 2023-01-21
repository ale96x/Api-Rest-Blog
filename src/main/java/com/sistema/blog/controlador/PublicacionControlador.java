package com.sistema.blog.controlador;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.entidades.Publicacion;
import com.sistema.blog.servicio.impl.PublicacionServicioImpl;
import com.sistema.blog.utilerias.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionControlador {

    @Autowired
    public PublicacionServicioImpl servicio;

    @GetMapping
    public ResponseEntity<List<PublicacionDTO>> obtenerTodasLasPublicaciones(){
        return new ResponseEntity<>(servicio.obtenerTodasLasPulicaciones(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable("id") Long id){
        return new ResponseEntity<>(servicio.obtenerPublicacionPorId(id),HttpStatus.OK);
    }

    @GetMapping("/paginacion")
    public ResponseEntity<Page<PublicacionDTO>> obtenerTodasLasPublicacionesPageable(
            @RequestParam(name = "page", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int page,
            @RequestParam(name = "size", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int size,
            @RequestParam(name = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(name = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir){
        Page<PublicacionDTO> pageResponse =servicio.obtenerTodasLasPulicacionesPaginacion(page, size, ordenarPor,sortDir);
        return new ResponseEntity<>(pageResponse,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PublicacionDTO> crearPublicacion(@RequestBody PublicacionDTO publicacionDTO){
        return new ResponseEntity<>(servicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacionPorId(@RequestBody PublicacionDTO publicacionDTO,@PathVariable("id") Long id){
        return new ResponseEntity<>(servicio.actualizarPublicacionPorId(publicacionDTO,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPublicacionPorId(@PathVariable(name = "id") Long id){
        servicio.borrarPublicacionPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
