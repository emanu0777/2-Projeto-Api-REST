
CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(10),
    complemento VARCHAR(20),
    bairro VARCHAR(40),
    cep VARCHAR(14),
    cidade VARCHAR(30),
    estado VARCHAR(3),
    ativo BOOLEAN NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, logradouro, numero, complemento , bairro, cep , cidade, estado, ativo)
VALUES ('Gustavo Martins', 'Rua da Saudade', '331', 'andar 2 ', 'Barueri', '65360', 'São Paulo', 'SP', true),
	   ('Samuel de Alcantara', 'Rua Cantigas da Saudade', '23', 'casa 2 ', 'Capao Redondo', '05090', 'São Paulo', 'SP', true),
	   ('Emanuel dos Santos Costa', 'Rua Cantigas da Saudade', '23', 'casa 2 ', 'Capao Redondo', '05090', 'São Paulo', 'SP', true),
	   ('Adna Kevelin ', 'Rua Cantigas da Saudade', '25', 'casa 1 ', 'Capao Redondo', '05090', 'São Paulo', 'SP', false),
	   ('Flor de Maria', 'Rua Afonso Pena', '337', 'Ao lado IASD ', 'Centro', '65360000', 'Maranha', 'MA', false);