--senhas= 123456

INSERT INTO public.tb_customer (email,"name","password") VALUES
	 ('rafamola@gmail.com','Rafael Marinho','$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');


INSERT INTO public.tb_customer_roles (customer_id,roles) VALUES
	 (1,1),
	 (1,2);