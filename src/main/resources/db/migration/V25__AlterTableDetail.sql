DROP TABLE public.train_detail;
DROP TABLE public.detail;

CREATE TABLE public.detail (
    id bigint NOT NULL PRIMARY KEY,
    name character varying(255),
    state smallint,
    distance_from_creation int,
    distance_from_repair int,
    train_id bigint NOT NULL REFERENCES public.train(id),
    detail_museum_id bigint NOT NULL REFERENCES public.detail_museum(id)
);