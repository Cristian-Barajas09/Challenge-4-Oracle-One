package com.foroAlura.controller;

import com.foroAlura.domain.usuario.Usuario;
import com.foroAlura.domain.usuario.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CursoControllerTest {

    private UsuarioRepository usuarioRepository;
    @Test
    void crear() {
        //given
        Usuario usuario = usuarioRepository.save(
                new Usuario()
        );
        //when
        //then
    }
}