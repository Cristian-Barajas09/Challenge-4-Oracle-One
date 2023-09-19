CREATE TABLE cursos_inpartidos(
    id BIGINT AUTO_INCREMENT NOT NULL,
    usuario_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_profesor FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_curso_inpartido FOREIGN KEY (curso_id) REFERENCES cursos(id)
);