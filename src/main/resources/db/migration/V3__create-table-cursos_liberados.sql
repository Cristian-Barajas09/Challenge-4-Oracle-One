CREATE TABLE cursos_liberados(
    id BIGINT AUTO_INCREMENT NOT NULL ,
    usuario_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_usuario_curso FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_curso_usuario FOREIGN KEY (curso_id) REFERENCES cursos(id)
);