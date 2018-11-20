ALTER TABLE train
ADD COLUMN city_id bigint;

ALTER TABLE ONLY public.train
    ADD CONSTRAINT city_train FOREIGN KEY (city_id) REFERENCES public.city(id);
