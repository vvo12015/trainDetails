CREATE TABLE public.game_config (
    id bigint NOT NULL PRIMARY KEY,
    name character varying(255),
    valueStr character varying(255),
    valueInt bigint
);