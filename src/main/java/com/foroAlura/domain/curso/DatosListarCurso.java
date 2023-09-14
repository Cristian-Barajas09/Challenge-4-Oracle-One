package com.foroAlura.domain.curso;

import java.time.LocalDateTime;

public record DatosListarCurso(
        String titulo,
        String descripcion,
        LocalDateTime fechaPublicacion,
        Long idProfesor
) {
    public DatosListarCurso(Curso curso){
        this(
                curso.getTitulo(),
                curso.getDescripcion(),
                curso.getFechaPublicacion(),
                curso.getProfesor().getId()
        );
    }
}
