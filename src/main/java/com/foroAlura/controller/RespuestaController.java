package com.foroAlura.controller;

import com.foroAlura.domain.curso.Curso;
import com.foroAlura.domain.curso.CursoRepository;
import com.foroAlura.domain.curso.DatosListarCurso;
import com.foroAlura.domain.respuesta.*;
import com.foroAlura.domain.topico.DatosRespuestaTopico;
import com.foroAlura.domain.topico.Topico;
import com.foroAlura.domain.topico.TopicoRepository;
import com.foroAlura.domain.usuario.DatosListadoUsuario;
import com.foroAlura.domain.usuario.Usuario;
import com.foroAlura.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/{idTopico}/respuestas")
@SecurityRequirement(name = "bearer-key")
@Tags({
        @Tag(name = "Respuestas")
})
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping
    public ResponseEntity<Page<DatosListarRespuesta>> obtenerRespuestasPorTopico(
            @PathVariable Long idTopico,
            Pageable page
    ){
        return ResponseEntity.ok(
                respuestaRepository.findByTopicoId(idTopico,page).map(respuesta -> {
                    DatosListadoUsuario datosListadoUsuario = new DatosListadoUsuario(
                            respuesta.getUsuario()
                    );
                    Curso curso = cursoRepository.getReferenceById(respuesta.getTopico().getCurso().getId());

                    DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                            respuesta.getTopico(),
                            datosListadoUsuario,
                            new DatosListarCurso(curso)
                    );

                    return new DatosListarRespuesta(
                            respuesta,
                            datosListadoUsuario,
                            datosRespuestaTopico
                    );
                })
        );
    }

    @PostMapping
    public ResponseEntity<DatosRespuesta> crearRespuesta(
            @PathVariable Long idTopico,
            @RequestBody DatosCrearRespuesta datos,
            UriComponentsBuilder uriComponentsBuilder
    ){
        Topico topico = topicoRepository.getReferenceById(idTopico);
        System.out.println(topico.getTitulo());
        Usuario usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        System.out.println(usuario.getNombre());
        Respuesta respuesta = respuestaRepository.save(new Respuesta(datos,topico,usuario));

        DatosListadoUsuario datosListadoUsuario = new DatosListadoUsuario(usuario);

        DatosRespuesta response = new DatosRespuesta(
                respuesta,
                datosListadoUsuario,
                new DatosRespuestaTopico(
                        topico,datosListadoUsuario,
                        new DatosListarCurso(cursoRepository.getReferenceById(topico.getCurso().getId()))
                )
        );

        URI url;

        url = uriComponentsBuilder.path("/{idTopico}/respuestas/{idRespuesta}").buildAndExpand(idTopico,respuesta.getId()).toUri();

        return ResponseEntity.created(url).body(response);

    }

    @DeleteMapping("/{idRespuesta}")
    public ResponseEntity eliminarTopico(
            @PathVariable Long idTopico,
            @PathVariable Long idRespuesta
    ){
        Respuesta respuesta = respuestaRepository.getReferenceById(idRespuesta);

        respuestaRepository.delete(respuesta);



        return ResponseEntity.noContent().build();
    }

}
