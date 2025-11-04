insert into modelo(nome, categoria, estilo) 
values ('Challenge', 'Streetwear', 'Casual'), ('Trucker', 'Streetwear', 'Casual');

insert into estampa(nome, tipo, posicao, descricao)
values
('Logo Bordado Adidas', 'Bordado', 'Frente', 'Logo clássico da Adidas bordada em alto relevo'),
('Logo Bordado Nike', 'Bordado', 'Frente', 'Logo clássico da Nike bordada em relevo paralelo'),
('Etiqueta Lateral', 'Patch', 'Lateral', 'Etiqueta de tecido com texto: Since 1980');

insert into material(nome)
values ('Algodão'), ('Couro');

insert into marca(nome)
values ('Nike'), ('Adidas');

insert into bone(nome, cor, categoria_aba, tamanho_aba, profundidade, circunferencia, bordado, id_material, id_marca, id_modelo)
values
('Boné Challenge Nike', 'Verde e Vermelho', 'Curva', 7, 15, '51-63.5', 1 , 1, 1),
('Boné Madrid Trucker Adidas', 'Marrom', 'Curva', 7, 16.5, '56-62', 2 , 2, 2);

insert into estoque(quantidade, data_atualizacao, id_bone)
values
(5, '2025-10-02', 1),
(10, '2025-10-02', 2);

insert into bone_estampa(id_bone, id_estampa)
values (1, 2), (1, 3), (2, 2);
