CREATE TABLE topicos(
    id BIGINT AUTO_INCREMENT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estatus_del_topico VARCHAR(100) NOT NULL,
    usuario_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_usuario_topico FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_curso_topico FOREIGN KEY (curso_id) REFERENCES cursos(id)
);