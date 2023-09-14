package com.foroAlura.domain.curso;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistrarCurso(
        @NotNull
        @NotBlank
        String titulo,
        @NotNull
        String descripcion,

        @NotNull
        @JsonAlias({"usuario_id"})
        Long usuarioId,
        @JsonAlias({"fecha_creacion"})
        LocalDateTime fechaCreacion,

        @NotNull
        @JsonAlias({"fecha_publicacion"})
        LocalDateTime fechaPublicacion
) {
}
