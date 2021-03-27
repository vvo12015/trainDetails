ALTER TABLE ONLY public.detail_museum
    DELETE COLUMN type;
ALTER TABLE ONLY public.detail_museum
    ADD COLUMN type_ref int;
ALTER TABLE ONLY public.detail_museum
    ADD COLUMN group_ref int;
ALTER TABLE ONLY public.detail_museum
    ADD COLUMN game_level_ref int;
ALTER TABLE ONLY public.detail_museum
    ADD COLUMN city_type_ref int;

CREATE TABLE public.detail_museum_type(
    id not null bigint,
    name character varying(255)
);

ALTER TABLE ONLY public.detail_museum_type
    ADD CONSTRAINT detail_museum_type_pkey PRIMARY KEY (id);

CREATE TABLE public.detail_museum_group(
    id not null bigint,
    name character varying(255)
);

ALTER TABLE ONLY public.detail_museum_group
    ADD CONSTRAINT detail_museum_group_pkey PRIMARY KEY (id);

CREATE TABLE public.game_level(
    id not null bigint,
    name character varying(255)
);

ALTER TABLE ONLY public.game_level
    ADD CONSTRAINT game_level_pkey PRIMARY KEY (id);

CREATE TABLE public.city_type(
    id not null bigint,
    name character varying(255)
);

ALTER TABLE ONLY public.city_type
    ADD CONSTRAINT city_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.detail_museum
    ADD CONSTRAINT detail_museum_type_type FOREIGN KEY (type_ref) REFERENCES public.detail_museum_type(id);

ALTER TABLE ONLY public.detail_museum
    ADD CONSTRAINT detail_museum_detail_museum_group FOREIGN KEY (group_ref) REFERENCES public.detail_museum_group(id);

ALTER TABLE ONLY public.detail_museum
    ADD CONSTRAINT game_level_detail_museum FOREIGN KEY (game_level_ref) REFERENCES public.game_level(id);

ALTER TABLE ONLY public.detail_museum
    ADD CONSTRAINT city_type_detail_museum FOREIGN KEY (city_type_ref) REFERENCES public.city_type(id);
