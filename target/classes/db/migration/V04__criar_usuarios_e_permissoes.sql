
CREATE TABLE permissao(
codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
descricao VARCHAR(30)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario(
codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
senha VARCHAR(100) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao(
	codigo_usuario BIGINT(20) NOT NULL,
    codigo_permissao BIGINT(20) NOT NULL,
    PRIMARY KEY (codigo_usuario, codigo_permissao),
    FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
    FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;