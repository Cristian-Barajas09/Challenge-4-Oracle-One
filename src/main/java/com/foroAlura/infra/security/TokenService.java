package com.foroAlura.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.foroAlura.domain.usuario.Usuario;
import com.foroAlura.infra.errors.Exceptions.TokenValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("JWT_SECRET")
    private String apiSecret;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //valida la firma
            return JWT.create()
                    .withIssuer("foro alura") //este emite quien lanzo el token
                    .withSubject(usuario.getEmail())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);

        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }
    }


    public String getSubject(String token){
        if(token == null){
            throw new TokenValidationError("el token es nulo");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

             verifier = JWT.require(algorithm)
                    .withIssuer("foro alura")
                    .build()
                     .verify(token)
             ;

            verifier.getSubject();

        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }

        if (verifier == null || verifier.getSubject() == null) {
            throw new TokenValidationError("Verifier invalid");
        }

        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }
}
