package com.foroAlura.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {
    Curso findByTitulo(String titulo);

    Curso findByUrlTitle(String nombreCurso);
}
