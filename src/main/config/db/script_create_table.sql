CREATE TABLE pedido(
  id BIGINT(20) AUTO_INCREMENT,
  numero_controle varchar(50) NOT NULL,
  data_cadastro date,
  nome_produto varchar(255) NOT NULL,
  valor_unitario double,
  quantidade int,
  codigo_cliente BIGINT NOT NULL,
  valor_total double,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pedido (numero_controle, data_cadastro, nome_produto, valor_unitario, quantidade, codigo_cliente, valor_total)
VALUES ('NC120', '2024-01-14', 'Produto A', 10.00, 1, 1001, 1000.00);

INSERT INTO pedido (numero_controle, data_cadastro, nome_produto, valor_unitario, quantidade, codigo_cliente, valor_total)
VALUES ('NC121', '2024-01-14', 'Produto B', 15.00, 2, 1001, 1000.00);

INSERT INTO pedido (numero_controle, data_cadastro, nome_produto, valor_unitario, quantidade, codigo_cliente, valor_total)
VALUES ('NC122', '2024-01-14', 'Produto C', 20.00, 3, 1001, 1000.00);

INSERT INTO pedido (numero_controle, data_cadastro, nome_produto, valor_unitario, quantidade, codigo_cliente, valor_total)
VALUES ('NC123', '2024-01-14', 'Produto D', 25.00, 4, 1001, 1000.00);

INSERT INTO pedido (numero_controle, data_cadastro, nome_produto, valor_unitario, quantidade, codigo_cliente, valor_total)
VALUES ('NC124', '2024-01-14', 'Produto E', 30.00, 5, 1001, 1000.00);

INSERT INTO pedido (numero_controle, data_cadastro, nome_produto, valor_unitario, quantidade, codigo_cliente, valor_total)
VALUES ('NC125', '2024-01-14', 'Produto F', 35.00, 6, 1001, 1000.00);

INSERT INTO pedido (numero_controle, data_cadastro, nome_produto, valor_unitario, quantidade, codigo_cliente, valor_total)
VALUES ('NC125', '2024-01-14', 'Produto G', 40.00, 6, 1001, 1000.00);

INSERT INTO pedido (numero_controle, data_cadastro, nome_produto, valor_unitario, quantidade, codigo_cliente, valor_total)
VALUES ('NC125', '2024-01-14', 'Produto H', 45.00, 6, 1001, 1000.00);

INSERT INTO pedido (numero_controle, data_cadastro, nome_produto, valor_unitario, quantidade, codigo_cliente, valor_total)
VALUES ('NC125', '2024-01-14', 'Produto I', 50.00, 7, 1001, 1000.00);

INSERT INTO pedido (numero_controle, data_cadastro, nome_produto, valor_unitario, quantidade, codigo_cliente, valor_total)
VALUES ('NC125', '2024-01-14', 'Produto J', 35.00, 8, 1001, 1000.00);