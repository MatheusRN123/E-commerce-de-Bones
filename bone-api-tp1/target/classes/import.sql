insert into material(nome) 
values('Algodão'), ('Couro');

insert into estoque(quantidade, data_atualizacao) 
values(5, '2025-10-02'), (10, '2025-10-02');

insert into marca(nome) 
values('Nike'), ('Adidas');

insert into bone(nome, cor, categoria_aba, tamanho_aba, profundidade, circunferencia, bordado, id_material, id_marca, id_estoque) 
values
('Boné Challenge MVCK', 'Verde e Vermelho', 'Curva', 7, 15, '51-63.5', 1 , 1, 1, 1),
('Boné Madrid Trucker MVCK', 'Marrom', 'Curva', 7, 16.5, '56-62', 2 , 2, 2, 2);