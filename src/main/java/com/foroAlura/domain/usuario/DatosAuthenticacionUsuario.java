package com.foroAlura.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosAuthenticacionUsuario(
        @NotNull
        String email,
        @NotNull
        String password
) {
}
