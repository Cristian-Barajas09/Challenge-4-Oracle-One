package com.foroAlura.domain.topico;

import com.foroAlura.domain.curso.Curso;
import com.foroAlura.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private EstatusTopicos estatusDelTopico;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;

    public Topico(DatosCrearTopico datos,Usuario usuario,Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        if(datos.fechaCreacion() != null){
            this.fechaCreacion = datos.fechaCreacion();
        } else {
            this.fechaCreacion = LocalDateTime.now();
        }
        this.usuario = usuario;
        this.curso = curso;
        if(datos.estatusTopicos() != null) {
            this.estatusDelTopico = datos.estatusTopicos();
        } else {
            this.estatusDelTopico = EstatusTopicos.SINRESPUESTA;
        }
    }

    public void actualizar(DatosCrearTopico datos) {
        if(datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }

    }
}
