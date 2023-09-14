package com.foroAlura.domain.topico;

import com.foroAlura.domain.curso.DatosListarCurso;
import com.foroAlura.domain.usuario.DatosListadoUsuario;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    EstatusTopicos estatus,
    @Valid
    DatosListadoUsuario usuario,
    @Valid
    DatosListarCurso curso
) {
    public DatosRespuestaTopico(Topico topico,DatosListadoUsuario usuario,DatosListarCurso curso) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),topico.getEstatusDelTopico(),usuario,curso);
    }

}
