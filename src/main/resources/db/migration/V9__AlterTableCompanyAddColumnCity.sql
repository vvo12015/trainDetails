ALTER TABLE company
ADD COLUMN city_id bigint;

ALTER TABLE ONLY public.company
    ADD CONSTRAINT city_company FOREIGN KEY (city_id) REFERENCES public.city(id);
