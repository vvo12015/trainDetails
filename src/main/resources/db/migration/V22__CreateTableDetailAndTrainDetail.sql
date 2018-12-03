CREATE TABLE public.detail (
    id bigint NOT NULL,
    name character varying(255),
    state smallint,
    distance_from_creation int,
    distance_from_repair int
);