package com.foroAlura.domain.respuesta;

import com.foroAlura.domain.topico.DatosRespuestaTopico;
import com.foroAlura.domain.usuario.DatosListadoUsuario;

import java.time.LocalDateTime;

public record DatosListarRespuesta(
        String mensaje,
        LocalDateTime fechaCreacion,
        DatosListadoUsuario usuario,
        DatosRespuestaTopico topico
) {
    public DatosListarRespuesta(
            Respuesta respuesta, DatosListadoUsuario usuario,DatosRespuestaTopico topico
    ){
        this(respuesta.getMensaje(), respuesta.getFechaCreacion(),usuario,topico);
    }
}
