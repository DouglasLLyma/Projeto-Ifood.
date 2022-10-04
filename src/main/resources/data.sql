INSERT INTO Restaurante (id, cep, complemento, nome ) VALUES
(1L, '02734-000', 'Complemento Endereço Restaurante 1', 'RestauranteColaCola'),
(2L, '02734-090', 'Complemento Endereço Restaurante 2', 'RestauranteChurrascaria');

INSERT INTO Cliente (id, cep, complemento, nome) VALUES
(1L, '01321-140', 'Complemento Endereço Cliente1','Douglas de Lima'),
(2L, '02921-399', 'Complemento Endereço Cliente2','Paula Renata');

INSERT INTO Produto (id, disponivel, nome, valor_Unitario, restaurante_id) VALUES
(1L, true, 'Produto 1', 5.0, 1L),
(2L, true, 'Produto 2', 6.0, 1L),
(3L, true, 'Produto 3', 7.0, 2L);

INSERT INTO Sacola (id, forma_pagamento, fechada, valor_total, cliente_id) VALUES
(1L, 0, false, 0.0, 1L);