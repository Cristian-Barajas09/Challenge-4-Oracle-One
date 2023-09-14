package com.foroAlura.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico,Long> {
    Topico findByTitulo(String titulo);

    Topico findByMensaje(String mensaje);
}
