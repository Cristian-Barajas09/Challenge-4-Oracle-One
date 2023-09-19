package com.foroAlura.domain.usuario;


import com.foroAlura.domain.curso.Curso;
import com.foroAlura.domain.respuesta.Respuesta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    private String email;

    private LocalDateTime fechaCreacion;

    private String password;

    @Enumerated(EnumType.STRING)
    private Roles rol;

    private Boolean status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cursos_liberados",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "profesor")
    private List<Curso> cursosInpartidos;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "usuario")
    private List<Respuesta> respuestas;


    public Usuario(DatosRegistrarUsuario usuario,String password){
        this.nombre = usuario.nombre();
        this.apellido = usuario.apellido();
        this.email = usuario.email();

        if(usuario.fechaCreacion() != null){
            this.fechaCreacion = usuario.fechaCreacion();
        } else {
            this.fechaCreacion = LocalDateTime.now();
        }


        this.password = password;
        this.rol = Roles.ESTUDIANTE;

        this.status = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getPassword(){return this.password;}

    @Override
    public String getUsername() {
        return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void actualizar(DatosRegistrarUsuario request) {
        if(request.nombre() != null){
            this.nombre = request.nombre();
        }
        if(request.apellido() != null) {
            this.apellido = request.apellido();
        }
        if(request.email() != null) {
            this.email = request.email();
        }
    }

    public void eliminar() {
        this.status = false;
    }

    public void changeRol(Roles rol) {this.rol = rol;}
}
