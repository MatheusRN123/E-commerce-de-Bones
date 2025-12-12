insert into modelo(nome, categoria, estilo)
values
('Challenge', 'Streetwear', 'Casual'),
('Trucker', 'Streetwear', 'Casual'),
('teste', 'teste', 'teste');

insert into material(nome)
values ('Algodão'), ('Couro'), ('teste');

insert into marca(nome)
values ('Nike'), ('Adidas'), ('teste');

insert into estoque(quantidade, data_atualizacao)
values (5, '2025-10-02'), (10, '2025-10-02'), (1, '2024-12-02'), (2, '2025-10-02');

insert into estampa(nome, tipo_estampa, posicao, descricao, cor_linha, qt_cores)
values
('Logo Bordado Adidas', 'BORDADA', 'Frente', 'Logo clássico da Adidas bordada em alto relevo', 'Branco', 1),
('Logo Bordado Nike', 'BORDADA', 'Frente', 'Logo clássico da Nike bordada em relevo paralelo', 'Branco', 1),
('teste', 'BORDADA', 'teste', 'teste', 'teste', 1);

insert into estampa(nome, tipo_estampa, posicao, descricao, resolucao)
values
('Etiqueta Lateral', 'DIGITAL', 'Lateral', 'Etiqueta em 1080p com texto: Since 1980', '1080p'),
('teste', 'DIGITAL', 'teste', 'teste', 'teste');

insert into bone(nome, cor, categoria_aba, tamanho_aba, profundidade, circunferencia, bordado, id_material, id_marca, id_modelo, id_estoque)
values
('Boné Challenge Nike', 'Verde e Vermelho', 'Curva', 7, 15, '51-63.5', 1, 1, 1, 1, 1),
('Boné Madrid Trucker Adidas', 'Marrom', 'Curva', 7, 16.5, '56-62', 2, 2, 2, 2, 2),
('teste', 'teste', 'teste', 1, 2, 'teste', 1, 1, 1, 1, 3);

insert into bone_estampa(id_bone, id_estampa)
values (1, 2), (1, 3), (2, 1);


insert into estado(nome, sigla) values
('São Paulo', 'SP'),
('Rio de Janeiro', 'RJ'),
('Minas Gerais', 'MG');

insert into cidade(nome, id_estado) values
('São Paulo', 1),
('Campinas', 1),
('Rio de Janeiro', 2),
('Belo Horizonte', 3);

insert into endereco(cep, logradouro, numero, id_cidade) values
('01001-000', 'Av. Paulista', '1000', 1),
('13010-000', 'Rua Boa Vista', '500', 2),
('20010-000', 'Av. Atlântica', '150', 3),
('30110-000', 'Rua da Bahia', '200', 3);

insert into usuario(nome, login, senha, perfil) values ('Matheus', 'matheus', 'BF4nuveEMMKY41dj5Kx8q06bISaGqniY6CDwaDjv/nh86Q97Fgxw+s9dQxyXy4VDlgpQX6N2zatkAqokrBAFqA==', 1);
insert into usuario(nome, login, senha, perfil) values ('Lucas', 'lucas', 'I9fCdxtIOOozwZn7u8dsq0lvSVLx9fuKpXuTK+rNlidGS+Ns78unZedOrx7MmiJXQR5TnryTAFRvlgXpoxSjLg==', 2);

INSERT INTO pagamento (valor, data, status) VALUES
(300.0, '2025-12-04 10:35:00', 'PAGO'),
(200.0, '2025-12-04 10:40:00', 'PENDENTE'),
(150.0, '2025-12-04 10:45:00', 'PAGO');

INSERT INTO pedido (data, id_usuario, id_endereco, id_pagamento) VALUES
('2025-12-04 10:30:00', 1, 1, 1),
('2025-12-04 11:00:00', 2, 2, 2);

INSERT INTO pix (id, chave, tipo_chave) VALUES
(1, '12345678900', 'CPF');

INSERT INTO boleto (id, codigo_barras, data_vencimento) VALUES
(2, '12345678901234567890', '2025-12-10');

INSERT INTO cartao (id, nome_titular, numero, validade, cvv) VALUES
(3, 'Ana Souza', '4111111111111111', '12/26', '123');


insert into item_pedido(id_pedido, id_bone, quantidade, preco) values
(1, 1, 2, 150.0),
(1, 2, 1, 200.0),
(2, 1, 1, 150.0);
