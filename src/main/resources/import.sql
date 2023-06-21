INSERT INTO estado (id, nome) VALUES (1, 'Minas Gerais');
INSERT INTO estado (id, nome) VALUES (2, 'São Paulo');
INSERT INTO estado (id, nome) VALUES (3, 'Ceará');

INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'Uberlândia', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (2, 'Belo Horizonte', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (3, 'São Paulo', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES (4, 'Campinas', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES (5, 'Fortaleza', 3);

INSERT INTO cozinha(id, nome) VALUES (1, 'Japonesa');
INSERT INTO cozinha(id, nome) VALUES (2, 'Havaiana');
INSERT INTO cozinha(id, nome) VALUES (3, 'Mineira');

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, data_cadastro, data_atualizacao) VALUES (1, 'Gusteaus', 11.50, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', utc_timestamp, utc_timestamp);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, data_cadastro, data_atualizacao) VALUES (2, 'Taberna', 15, 2, 2, '38400-888', 'Rua Chico Marreco', '19', 'Peninsula', utc_timestamp, utc_timestamp);

-- INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id) VALUES (1, 'Gusteaus', 11.50, 1);
-- INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id) VALUES (2, 'Taberna', 15, 2);

INSERT INTO produto(id, nome, descricao, preco, ativo, restaurante_id) VALUES (1, "Pizza Nordestina", "Carne de sol, manteiga de garrafa", 39.99, true, 1);
INSERT INTO produto(id, nome, descricao, preco, ativo, restaurante_id) VALUES (2, "Frango Grelhado", "Oregano e sal", 22, true, 2);

INSERT INTO forma_pagamento(id, descricao) VALUES (1, 'CARTÃO DE CRÉDITO');
INSERT INTO forma_pagamento(id, descricao) VALUES (2, 'CARTÃO DE DÉBITO');
INSERT INTO forma_pagamento(id, descricao) VALUES (3, 'PIX');

INSERT INTO permissao(id, descricao, nome) VALUES (1, 'Validar', 'Valida informações');
INSERT INTO permissao(id, descricao, nome) VALUES (2, 'Registrar', 'Registra informações');
INSERT INTO permissao(id, descricao, nome) VALUES (3, 'Consulta', 'Consulta informações');

INSERT INTO restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3)