INSERT INTO cozinha(id, nome) VALUES (1, 'Japonesa');
INSERT INTO cozinha(id, nome) VALUES (2, 'Havaiana');
INSERT INTO cozinha(id, nome) VALUES (3, 'Mineira');

INSERT INTO restaurante(id, nome, taxa_frete, cozinha_id) VALUES (1, 'Gusteaus', 14.99, 3);
INSERT INTO restaurante(id, nome, taxa_frete, cozinha_id) VALUES (2, 'Taberna', 14.99, 2);

INSERT INTO forma_pagamento(id, descricao) VALUES (1, 'CARTÃO DE CRÉDITO');
INSERT INTO forma_pagamento(id, descricao) VALUES (2, 'CARTÃO DE DÉBITO');
INSERT INTO forma_pagamento(id, descricao) VALUES (3, 'PIX');

INSERT INTO permissao(id, descricao, nome) VALUES (1, 'Validar', 'Valida informações');
INSERT INTO permissao(id, descricao, nome) VALUES (2, 'Registrar', 'Registra informações');
INSERT INTO permissao(id, descricao, nome) VALUES (3, 'Consulta', 'Consulta informações');

INSERT INTO estado (id, nome) values (1, 'Minas Gerais');
INSERT INTO estado (id, nome) values (2, 'São Paulo');
INSERT INTO estado (id, nome) values (3, 'Ceará');

INSERT INTO cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
INSERT INTO cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
INSERT INTO cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
INSERT INTO cidade (id, nome, estado_id) values (4, 'Campinas', 2);
INSERT INTO cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);