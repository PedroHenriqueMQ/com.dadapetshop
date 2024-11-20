CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    quantidade_estoque INT NOT NULL DEFAULT 0,
    quantidade_prateleira INT NOT NULL DEFAULT 0,
    valor NUMERIC(10, 2) NOT NULL,
    categoria VARCHAR(20) NOT NULL
);