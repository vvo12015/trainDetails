DROP TABLE public.detail;
DROP TABLE public.train_detail;

CREATE TABLE public.detail (
    id bigint NOT NULL PRIMARY KEY,
    name character varying(255),
    state smallint,
    distance_from_creation int,
    distance_from_repair int
);

CREATE TABLE public.train_detail (
train_id bigint NOT NULL REFERENCES public.train(id),
detail_id bigint NOT NULL REFERENCES public.detail(id)
);