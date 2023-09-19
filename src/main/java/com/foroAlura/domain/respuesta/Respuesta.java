package com.foroAlura.domain.respuesta;

import com.foroAlura.domain.topico.Topico;
import com.foroAlura.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    private LocalDateTime fechaCreacion;



    @JoinTable(
            name = "respuestas_usuarios",
            joinColumns = @JoinColumn(name = "respuesta_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @JoinTable(
            name ="respuestas_topicos",
            joinColumns = @JoinColumn(name = "respuesta_id"),
            inverseJoinColumns = @JoinColumn(name = "topico_id")
    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Topico topico;

    public Respuesta(DatosCrearRespuesta datos, Topico topico, Usuario usuario) {
        this.mensaje = datos.mensaje();
        if(datos.fechaCreacion() != null){
            this.fechaCreacion = datos.fechaCreacion();
        } else {
            this.fechaCreacion = LocalDateTime.now();
        }
        this.usuario = usuario;
        this.topico = topico;
    }
}
