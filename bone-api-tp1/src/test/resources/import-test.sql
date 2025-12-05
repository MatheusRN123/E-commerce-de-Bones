-- ============================================
-- SCRIPT DE TESTES COMPLETO
-- Usando id_ para nomes de colunas
-- ============================================

-- 1. ESTADO (base para cidade)
INSERT INTO estado (nome, sigla) VALUES 
('São Paulo', 'SP'),
('Rio de Janeiro', 'RJ'),
('Minas Gerais', 'MG');

-- 2. CIDADE (depende de estado)
INSERT INTO cidade (nome, id_estado) VALUES 
('São Paulo', 1),
('Campinas', 1),
('Rio de Janeiro', 2),
('Belo Horizonte', 3);

-- 3. USUÁRIO (base para pedido)
INSERT INTO usuario (nome, login, senha, perfil) VALUES 
('Matheus', 'matheus', 'BF4nuveEMMKY41dj5Kx8q06bISaGqniY6CDwaDjv/nh86Q97Fgxw+s9dQxyXy4VDlgpQX6N2zatkAqokrBAFqA==', 1),
('Lucas', 'lucas', 'I9fCdxtIOOozwZn7u8dsq0lvSVLx9fuKpXuTK+rNlidGS+Ns78unZedOrx7MmiJXQR5TnryTAFRvlgXpoxSjLg==', 2);

-- 4. ENDEREÇO (depende de cidade)
INSERT INTO endereco (cep, logradouro, numero, id_cidade) VALUES 
('01001-000', 'Av. Paulista', '1000', 1),
('13010-000', 'Rua Boa Vista', '500', 2),
('20010-000', 'Av. Atlântica', '150', 3),
('30110-000', 'Rua da Bahia', '200', 4);

-- 5. PAGAMENTO (base para pedido)
INSERT INTO pagamento (valor, data, status) VALUES 
(300.0, '2025-12-04 10:35:00', 'PAGO'),
(200.0, '2025-12-04 10:40:00', 'PENDENTE'),
(150.0, '2025-12-04 10:45:00', 'PAGO');

-- 6. PEDIDO (depende de usuario, endereco e pagamento)
INSERT INTO pedido (data, id_usuario, id_endereco, id_pagamento) VALUES 
('2025-12-04 10:30:00', 1, 1, 1),
('2025-12-04 11:00:00', 2, 2, 2);

-- 7. PIX (herda de pagamento)
INSERT INTO pix (id, chave, tipo_chave) VALUES 
(1, '12345678900', 'CPF');

-- 8. BOLETO (herda de pagamento)
INSERT INTO boleto (id, codigo_barras, data_vencimento) VALUES 
(2, '12345678901234567890', '2025-12-10');

-- 9. CARTAO (herda de pagamento)
INSERT INTO cartao (id, nome_titular, numero, validade, cvv) VALUES 
(3, 'Ana Souza', '4111111111111111', '12/26', '123');

-- 10. MARCA (para bone)
INSERT INTO marca (nome) VALUES 
('Nike'),
('Adidas'),
('Marca Teste');

-- 11. MODELO (para bone)
INSERT INTO modelo (nome, categoria, estilo) VALUES 
('Challenge', 'Streetwear', 'Casual'),
('Trucker', 'Streetwear', 'Casual'),
('Modelo Teste', 'Teste', 'Teste');

-- 12. MATERIAL (para bone)
INSERT INTO material (nome) VALUES 
('Algodão'),
('Couro'),
('Material Teste');

-- 13. ESTOQUE (para bone)
INSERT INTO estoque (quantidade, data_atualizacao) VALUES 
(5, '2025-10-02'),
(10, '2025-10-02'),
(1, '2024-12-02'),
(2, '2025-10-02');

-- 14. ESTAMPA (para bone_estampa)
INSERT INTO estampa (nome, tipo_estampa, posicao, descricao, cor_linha, qt_cores) VALUES 
('Logo Bordado Adidas', 'BORDADA', 'Frente', 'Logo clássico da Adidas bordada em alto relevo', 'Branco', 1),
('Logo Bordado Nike', 'BORDADA', 'Frente', 'Logo clássico da Nike bordada em relevo paralelo', 'Branco', 1),
('Estampa Teste', 'BORDADA', 'teste', 'teste', 'teste', 1);

INSERT INTO estampa (nome, tipo_estampa, posicao, descricao, resolucao) VALUES 
('Etiqueta Lateral', 'DIGITAL', 'Lateral', 'Etiqueta em 1080p com texto: Since 1980', '1080p'),
('Estampa Digital Teste', 'DIGITAL', 'teste', 'teste', 'teste');

-- 15. BONE (depende de material, marca, modelo, estoque)
INSERT INTO bone (nome, cor, categoria_aba, tamanho_aba, profundidade, circunferencia, bordado, id_material, id_marca, id_modelo, id_estoque) VALUES 
('Boné Challenge Nike', 'Verde e Vermelho', 'Curva', 7, 15, '51-63.5', 1, 1, 1, 1, 1),
('Boné Madrid Trucker Adidas', 'Marrom', 'Curva', 7, 16.5, '56-62', 2, 2, 2, 2, 2),
('Bone Teste', 'Preto', 'Curva', 7, 15, '50-60', 1, 3, 3, 3, 3);

-- 16. BONE_ESTAMPAS (tabela de relacionamento)
INSERT INTO bone_estampa (id_bone, id_estampa) VALUES 
(1, 2),
(1, 3),
(2, 1);

-- 17. ITEM_PEDIDO (depende de pedido e bone)
INSERT INTO item_pedido (id_pedido, id_bone, quantidade, preco) VALUES 
(1, 1, 2, 150.0),
(1, 2, 1, 200.0),
(2, 1, 1, 150.0);