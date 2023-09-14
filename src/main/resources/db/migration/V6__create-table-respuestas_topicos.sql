CREATE TABLE respuestas_topicos(
    id BIGINT AUTO_INCREMENT NOT NULL,
    topico_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_topico_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_topico_respuesta FOREIGN KEY (topico_id) REFERENCES topicos(id)
);