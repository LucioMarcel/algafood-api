set foreign_key_checks = 0;

delete from estado;
delete from cidade;
delete from usuario;
delete from grupo;
delete from usuario_grupo;
delete from permissao;
delete from grupo_permissao;
delete from restaurante;
delete from cozinha;
delete from forma_pagamento;
delete from restaurante_forma_pagamento; 
delete from produto;
delete from restaurante_usuario_responsavel;
delete from pedido;
delete from item_pedido;

set foreign_key_checks = 1;

alter table estado auto_increment = 1;
alter table cidade auto_increment = 1;
alter table usuario auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table produto auto_increment = 1;

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id,data_cadastro,data_atualizacao, ativo, aberto) values ('Thai Gourmet', 10, 1,'90870-020','Rua','340','403','Gloria',1,utc_timestamp,utc_timestamp, true, true);
insert into restaurante (nome, taxa_frete, cozinha_id,data_cadastro,data_atualizacao, ativo, aberto) values ('Thai Delivery', 9.50, 1,utc_timestamp,utc_timestamp, true, true);
insert into restaurante (nome, taxa_frete, cozinha_id,data_cadastro,data_atualizacao, ativo, aberto) values ('Tuk Tuk Comida Indiana', 15, 2,utc_timestamp,utc_timestamp, true, true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values ('Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values ('Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values ('Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true, true);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into grupo (id, nome) values (1, 'Gerente');
insert into grupo (id, nome) values (2, 'Administrador');
insert into grupo (id, nome) values (3, 'Secretaria');
insert into grupo (id, nome) values (4, 'Cadastrador');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissao (id, nome, descricao) values (3, 'EDITAR_USUARIOS', 'Permite editar usuários');
insert into permissao (id, nome, descricao) values (4, 'EDITAR_PERMISSOES', 'Permite editar permissões');
insert into permissao (id, nome, descricao) values (5, 'EDITAR_GRUPOS', 'Permite editar grupos');

insert into grupo_permissao (grupo_id, permissao_id) values (1,1), (1,2), (1,3), (2,1), (2,2), (2,3), (2,4), (2,5), (3,1), (3,3), (4,1), (4,4);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into usuario (id, nome, email, senha, data_cadastro) values (1, 'Lúcio', 'lucio@gmail.com','123', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (2, 'Pedro', 'pedro@gmail.com','123', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (3, 'João', 'joao@gmail.com','123', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (4, 'José', 'jose@gmail.com','123', utc_timestamp);

insert into usuario_grupo (usuario_id, grupo_id) values (1,1), (1,2), (2,2), (2,4), (4,1),  (4,2), (4,4);  

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1,2), (2,1), (3,4), (4,3);

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
    status, data_criacao, subtotal, taxa_frete, valor_total)
values (1, 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
        endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
        status, data_criacao, subtotal, taxa_frete, valor_total)
values (2, 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
'CRIADO', utc_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (3, 2, 6, 1, 79, 79, 'Ao ponto');

