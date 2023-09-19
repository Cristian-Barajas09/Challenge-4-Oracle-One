package com.foroAlura.domain.respuesta;

import com.foroAlura.domain.topico.DatosRespuestaTopico;
import com.foroAlura.domain.usuario.DatosListadoUsuario;


import java.time.LocalDateTime;

public record DatosRespuesta(
        String mensaje,
        LocalDateTime fechaCreacion,
        DatosListadoUsuario usuario,
        DatosRespuestaTopico topico
) {
    public DatosRespuesta (Respuesta respuesta,DatosListadoUsuario usuario,DatosRespuestaTopico topico){
        this(respuesta.getMensaje(),respuesta.getFechaCreacion(),usuario,topico);
    }
}
