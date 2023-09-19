package com.foroAlura.domain.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DatosCambiarRol(
        @NotNull
        Roles rol,
        @NotNull
        @JsonAlias({"usuario_id"})
        Long idUsuario
) {
}
