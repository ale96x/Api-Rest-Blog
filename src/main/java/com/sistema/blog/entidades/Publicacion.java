package com.sistema.blog.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "publicaciones") //uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo"})}    Se indica que la columna titulo sera unico
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo",nullable = false, unique = true)
    private String titulo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true) //Con esto al eliminar una publicacion se eliminan los comentarios asociados a ella
    Set<Comentario> comentarios = new HashSet<>();
}
