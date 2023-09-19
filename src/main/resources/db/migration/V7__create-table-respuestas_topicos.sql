CREATE TABLE respuestas_topicos(
    id BIGINT AUTO_INCREMENT NOT NULL,
    respuesta_id BIGINT NOT NULL,
    topico_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_respuesta_topico FOREIGN KEY (respuesta_id) REFERENCES respuestas(id),
    CONSTRAINT fk_topico_respuesta FOREIGN KEY (topico_id) REFERENCES topicos(id)
);