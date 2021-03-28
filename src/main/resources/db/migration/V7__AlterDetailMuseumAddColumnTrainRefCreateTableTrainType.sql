ALTER TABLE ONLY public.detail_museum
    ADD COLUMN train_type_ref bigint;

CREATE TABLE public.train_type(
    id bigint NOT NULL,
    name character varying(255)
);

ALTER TABLE ONLY public.train_type
    ADD CONSTRAINT train_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.detail_museum
    ADD CONSTRAINT detail_museum_train_type FOREIGN KEY (train_type_ref) REFERENCES public.train_type(id);