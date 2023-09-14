package com.foroAlura.infra.security;

import com.foroAlura.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("este es el inicio del filtro");

        //el token se obtiene a traves de los headers
        var token = recuperarToken(request);
        System.out.println(token);
        if(token != null) {
            token = token.replace("Bearer ","");
            var subject = tokenService.getSubject(token);
            System.out.println("sevicio " + subject);
            if (subject != null) {
                //token valido
                var usuario = usuarioRepository.findByEmail(subject);
                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        usuario.getAuthorities());
                System.out.println("authentication " +authentication);
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);


    }
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        System.out.println("auto "+ authorizationHeader);
        if (authorizationHeader != null) {
            int startIndex = "Bearer ".length();
            if (authorizationHeader.length() > startIndex) {
                return authorizationHeader.substring(startIndex);
            }
        }

        return null;
    }
}
