--Arquivo usado somente com o bando de dados em memória H2 para testes
--senhas= 123456

INSERT INTO tb_customer (name, email, password) VALUES ('Nina Brown', 'nina@gmail.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');
INSERT INTO tb_customer (name, email, password) VALUES ('Leia Red', 'leia@gmail.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');


INSERT INTO tb_customer_roles (customer_id, roles) VALUES (1, 1);
INSERT INTO tb_customer_roles (customer_id, roles) VALUES (2, 1);
INSERT INTO tb_customer_roles (customer_id, roles) VALUES (2, 2);