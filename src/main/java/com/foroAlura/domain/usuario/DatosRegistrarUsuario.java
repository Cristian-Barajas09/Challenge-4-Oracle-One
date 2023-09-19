package com.foroAlura.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistrarUsuario(
        @NotNull
        @NotBlank
        String nombre,
        @NotNull
        @NotBlank
         String apellido,
        @NotNull
        @NotBlank
        @Email
        String email,
         LocalDateTime fechaCreacion,
        @NotNull
        @NotBlank
        String password
) {
}
