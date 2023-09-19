package com.foroAlura.controller;

import com.foroAlura.domain.usuario.*;
import com.foroAlura.infra.security.DatosJWTToken;
import com.foroAlura.infra.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping
@Tags({
        @Tag(name = "autenticacion")
})
public class AuthenticationController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/registro")
    public ResponseEntity<DatosRespuestaUsuario> create(@RequestBody @Valid DatosRegistrarUsuario request, UriComponentsBuilder uriComponentsBuilder){

        String newPassword = BCrypt.hashpw(request.password(),BCrypt.gensalt());

        Usuario usuario = usuarioRepository.save(new Usuario(request,newPassword));
        System.out.println(usuario);
        DatosRespuestaUsuario response = new DatosRespuestaUsuario(usuario);
        URI url;
        url = uriComponentsBuilder.path("/{idUsuario}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @PostMapping("/ingresar")
    public ResponseEntity<DatosJWTToken> login(@RequestBody DatosAuthenticacionUsuario request){
        System.out.println("hola");
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        );

        System.out.println(request.email());
        System.out.println(authenticationToken);
        var usuarioAutenticado = authenticationManager.authenticate(
                authenticationToken
        );


        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}
