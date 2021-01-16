--Arquivo usado somente com o bando de dados POSTGRES para dev


CREATE TABLE public.tb_customer (
	id bigserial NOT NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	"password" varchar(255) NULL,
	CONSTRAINT tb_customer_pkey PRIMARY KEY (id),
	CONSTRAINT uk_a9xeibptr987g1od1c430m1w9 UNIQUE (email)
);


CREATE TABLE public.tb_customer_roles (
	customer_id int8 NOT NULL,
	roles int4 NULL,
	CONSTRAINT fkd9n6koc9qvhek5po5bmqqc5nq FOREIGN KEY (customer_id) REFERENCES tb_customer(id)
);