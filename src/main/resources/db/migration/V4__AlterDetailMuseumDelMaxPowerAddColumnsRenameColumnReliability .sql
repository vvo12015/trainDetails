ALTER TABLE ONLY public.detail_museum
    RENAME COLUMN max_power TO max_mass,
    RENAME COLUMN reliability TO start_reliability,
    ADD COLUMN max_speed int,
    ADD COLUMN start_date_production date,
    ADD COLUMN price int;