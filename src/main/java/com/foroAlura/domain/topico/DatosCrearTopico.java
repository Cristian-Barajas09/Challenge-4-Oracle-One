package com.foroAlura.domain.topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosCrearTopico(
        @NotNull
        @NotBlank
        String titulo,
        @NotNull
        @NotBlank
        String mensaje,
        @JsonAlias({"fecha_creacion"})
        LocalDateTime fechaCreacion,
        @JsonAlias({"estatus"})
        EstatusTopicos estatusTopicos,
        @NotNull
        @JsonAlias({"usuario_id","autor_id"})
        Long idUsuario,
        @NotNull
        @JsonAlias({"curso_id"})
        Long idCurso
) {
}
