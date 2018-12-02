DROP TABLE train_detail_museum;

CREATE TABLE public.train_detail_museum (
train_museum_id bigint NOT NULL,
detail_id bigint NOT NULL
);

ALTER TABLE ONLY public.train_detail_museum
ADD CONSTRAINT fkf2lxty4na57h32rtjg9yy37ch FOREIGN KEY (detail_id) REFERENCES public.detail_museum(id);

ALTER TABLE ONLY public.train_detail_museum
ADD CONSTRAINT fkpdval0snai5dos6g87x5spc6v FOREIGN KEY (train_museum_id) REFERENCES public.train_museum(id);
