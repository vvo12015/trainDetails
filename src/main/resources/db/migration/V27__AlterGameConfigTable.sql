DROP table public.game_config;

CREATE TABLE public.game_config(
    id bigint NOT NULL PRIMARY KEY,
    name character varying(255),
    value_str character varying(255),
    value_int bigint
);