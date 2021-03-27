ALTER TABLE ONLY public.detail_museum
    RENAME COLUMN max_power TO max_mass;
ALTER TABLE ONLY public.detail_museum
    RENAME COLUMN reliability TO start_reliability;
ALTER TABLE ONLY public.detail_museum
    ADD COLUMN max_speed int;
ALTER TABLE ONLY public.detail_museum
    ADD COLUMN start_date_production date;
ALTER TABLE ONLY public.detail_museum
    ADD COLUMN price int;