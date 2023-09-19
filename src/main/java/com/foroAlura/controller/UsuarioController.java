package com.foroAlura.controller;

import com.foroAlura.domain.usuario.*;

import com.foroAlura.infra.errors.Exceptions.EmailAlReadyExistsError;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/usuarios")
@Tags({
        @Tag(name = "usuarios")
})
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @SecurityRequirement(name="bearer-key")
    @GetMapping("/{idUsuario}")
    public ResponseEntity<DatosListadoUsuario> getByid(@PathVariable Long idUsuario){
        DatosListadoUsuario response = new DatosListadoUsuario(usuarioRepository.getReferenceById(idUsuario));

        return ResponseEntity.ok(response);
    }
    @SecurityRequirement(name="bearer-key")
    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> getAll(Pageable paginacion){
        return ResponseEntity.ok(usuarioRepository.findByStatusTrue(paginacion).map(DatosListadoUsuario::new));
    }
    @SecurityRequirement(name="bearer-key")
    @PutMapping("/{idUsuario}")
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> update(@PathVariable Long idUsuario, @RequestBody DatosRegistrarUsuario request){
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);

        if(usuarioRepository.findByEmail(request.email()) != null) {
            throw new EmailAlReadyExistsError("el correo ya existe");
        }

        usuario.actualizar(request);


        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }


    @SecurityRequirement(name="bearer-key")
    @DeleteMapping("/{idUsuario}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long idUsuario){
        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        usuario.eliminar();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change-rol")
    @Transactional
    @SecurityRequirement(name="bearer-key")
    public ResponseEntity cambiarRol(
        @RequestBody DatosCambiarRol rol
    ){
        Usuario usuario = usuarioRepository.getReferenceById(rol.idUsuario());
        usuario.changeRol(rol.rol());

        return ResponseEntity.ok().build();
    }
}
