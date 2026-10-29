CREATE TABLE imoveis (
    id BIGSERIAL PRIMARY KEY,
    endereco VARCHAR(255) NOT NULL,
    valor_aluguel DOUBLE PRECISION,
    descricao VARCHAR(500)
);
