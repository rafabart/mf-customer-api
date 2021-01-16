--Arquivo usado somente com o bando de dados POSTGRES para dev
--senhas= 123456

INSERT INTO public.tb_customer (email,"name","password") VALUES
	 ('nina@gmail.com','Nina Brown','$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu'),
	 ('leia@gmail.com','Leia Red','$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');


INSERT INTO public.tb_customer_roles (customer_id,roles) VALUES
	 (1,1),
	 (2,1),
	 (2,2);