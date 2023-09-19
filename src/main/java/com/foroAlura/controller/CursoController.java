package com.foroAlura.controller;

import com.foroAlura.domain.curso.*;
import com.foroAlura.domain.usuario.Usuario;
import com.foroAlura.domain.usuario.UsuarioRepository;
import com.foroAlura.infra.errors.Exceptions.TituloAlReadyExistsError;
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
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
@Tags({
        @Tag(name = "Cursos")
})
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> crear(
            @RequestBody DatosRegistrarCurso datos,
            UriComponentsBuilder uriComponentsBuilder
    ){
        System.out.println("wiiiii");
        if(cursoRepository.findByTitulo(datos.titulo()) != null){
            throw new TituloAlReadyExistsError("el titulo ya existe");
        }
        if(!usuarioRepository.existsById(datos.usuarioId())){
            throw new RuntimeException("el usuario no existe");
        }


        String urlTitle = datos.titulo().replace(" ","-");

        Usuario usuario = usuarioRepository.getReferenceById(datos.usuarioId());

        Curso curso = cursoRepository.save(new Curso(datos,urlTitle,usuario));
        DatosRespuestaCurso response = new DatosRespuestaCurso(curso);

        URI url;

        url = uriComponentsBuilder.path("/cursos/{nombreCurso}").buildAndExpand(curso.getUrlTitle()).toUri();

        return ResponseEntity.created(url).body(response);
    }
    @GetMapping
    public ResponseEntity<Page<DatosListarCurso>> obtenerCursos(
            Pageable page
    ){
        return ResponseEntity.ok(cursoRepository.findAll(page).map(DatosListarCurso::new));
    }

    @GetMapping("/{nombreCurso}")
    public ResponseEntity<DatosListarCurso> obtenerCurso(@PathVariable String nombreCurso){
        Curso curso = cursoRepository.findByUrlTitle(nombreCurso);

        return ResponseEntity.ok(new DatosListarCurso(curso));
    }
}
