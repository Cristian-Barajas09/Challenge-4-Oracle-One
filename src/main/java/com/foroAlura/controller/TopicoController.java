package com.foroAlura.controller;

import com.foroAlura.domain.curso.Curso;
import com.foroAlura.domain.curso.CursoRepository;
import com.foroAlura.domain.curso.DatosListarCurso;
import com.foroAlura.domain.topico.*;
import com.foroAlura.domain.usuario.DatosListadoUsuario;
import com.foroAlura.domain.usuario.Usuario;
import com.foroAlura.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name="bearer-key")
@Tags({
        @Tag(name = "Topicos")
})
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crearTopico(
            @RequestBody DatosCrearTopico datos,
            UriComponentsBuilder uriComponentsBuilder
    ){
        if(topicoRepository.findByTitulo(datos.titulo()) != null) {
            throw new RuntimeException("el titulo del topico ya esta registrado");
        }

        if(topicoRepository.findByMensaje(datos.mensaje()) != null){
            throw new RuntimeException("el titulo del topico ya esta registrado");
        }

        Curso curso = cursoRepository.getReferenceById(datos.idCurso());
        Usuario usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        Topico topico = topicoRepository.save(new Topico(datos,usuario,curso));

        DatosRespuestaTopico response = new DatosRespuestaTopico(topico,new DatosListadoUsuario(usuario),new DatosListarCurso(curso));

        URI url;
        url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(response);

    }
    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> obtenerTopicos(
            Pageable page
    ){
        return ResponseEntity.ok(topicoRepository.findAll(page).map(topico -> {
                    DatosListadoUsuario datosListadoUsuario = new DatosListadoUsuario(usuarioRepository.getReferenceById(topico.getUsuario().getId()));
                    DatosListarCurso datosListarCurso = new DatosListarCurso(cursoRepository.getReferenceById(topico.getCurso().getId()));
                    return new DatosRespuestaTopico(topico, datosListadoUsuario, datosListarCurso);

        }));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerTopico(
            @PathVariable Long id
    ){
        Topico topico =topicoRepository.getReferenceById(id);
        DatosListadoUsuario datosListadoUsuario = new DatosListadoUsuario(usuarioRepository.getReferenceById(topico.getUsuario().getId()));
        DatosListarCurso datosListarCurso = new DatosListarCurso(cursoRepository.getReferenceById(topico.getCurso().getId()));
        DatosRespuestaTopico response = new DatosRespuestaTopico(topico,datosListadoUsuario,datosListarCurso);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @PathVariable Long id,
            @RequestBody DatosCrearTopico datos
    ) {
        Topico topico = topicoRepository.getReferenceById(id);

        DatosListadoUsuario usuario = new DatosListadoUsuario(usuarioRepository.getReferenceById(topico.getUsuario().getId()));
        DatosListarCurso curso = new DatosListarCurso(cursoRepository.getReferenceById(topico.getCurso().getId()));
        topico.actualizar(datos);

        return ResponseEntity.ok(new DatosRespuestaTopico(topico,usuario,curso));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}

