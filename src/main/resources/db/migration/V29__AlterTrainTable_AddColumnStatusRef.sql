CREATE TABLE public.train_status(
    id bigint NOT NULL PRIMARY KEY,
    name character varying(255)
);

COPY public.train_status (id, name) FROM stdin;
1	PROGRESS
2	SOLD
3   BREAKAGE

ALTER table train
ADD COLUMN status_ref bigint;

ALTER TABLE ONLY public.train
    ADD CONSTRAINT train_train_status FOREIGN KEY (status_ref) REFERENCES public.train_status(id);
