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
