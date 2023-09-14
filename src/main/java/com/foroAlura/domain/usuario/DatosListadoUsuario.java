package com.foroAlura.domain.usuario;

public record DatosListadoUsuario(
        String nombre,
        String apellido,
        String email
) {

    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getNombre(),usuario.getApellido(),usuario.getEmail());
    }
}
