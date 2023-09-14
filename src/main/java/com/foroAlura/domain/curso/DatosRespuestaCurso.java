package com.foroAlura.domain.curso;

import java.time.LocalDateTime;

public record DatosRespuestaCurso(
        String titulo,
        LocalDateTime fechaCreacion
) {
    public DatosRespuestaCurso(Curso curso){
        this(
                curso.getTitulo(),curso.getFechaCreacion()
        );
    }
}
