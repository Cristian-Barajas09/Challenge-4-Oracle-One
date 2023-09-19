CREATE TABLE respuestas_usuarios(
    id BIGINT AUTO_INCREMENT NOT NULL,
    usuario_id BIGINT NOT NULL,
    respuesta_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_usuario_respuesta FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_respuesta_usuario FOREIGN KEY (respuesta_id) REFERENCES respuestas(id)
);