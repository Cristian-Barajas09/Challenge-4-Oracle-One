package com.foroAlura.domain.usuario;

import java.time.LocalDateTime;

public record DatosRespuestaUsuario(
        String name,
        String email,
        LocalDateTime fechaCreacion
) {
    public  DatosRespuestaUsuario(Usuario usuario){
        this(usuario.getNombre(),usuario.getEmail(),usuario.getFechaCreacion());
    }
}
