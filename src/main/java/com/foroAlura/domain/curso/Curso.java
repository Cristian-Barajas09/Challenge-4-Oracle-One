package com.foroAlura.domain.curso;

import com.foroAlura.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cursos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaPublicacion;
    private String urlTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cursos_inpartidos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Usuario profesor;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "cursos")
    private List<Usuario> estudiantes;


    public Curso(DatosRegistrarCurso datos,String urlTitle,Usuario profesor) {
        this.titulo = datos.titulo();
        this.descripcion = datos.descripcion();
        if(datos.fechaCreacion() != null){
            this.fechaCreacion = datos.fechaCreacion();
        } else {
            this.fechaCreacion = LocalDateTime.now();
        }
        this.fechaPublicacion = datos.fechaPublicacion();
        this.urlTitle = urlTitle;
        this.profesor = profesor;
    }
}
