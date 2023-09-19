package com.foroAlura.domain.respuesta;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosCrearRespuesta(
        @NotNull
        String mensaje,
        LocalDateTime fechaCreacion,
        @NotNull
        @JsonAlias({"usuario_id"})
        Long idUsuario
) {
}
