ALTER TABLE ONLY public.detail_museum
    DROP COLUMN type_ref,
    DROP COLUMN group_ref,
    DROP COLUMN game_level_ref,
    DROP COLUMN city_type_ref;
ALTER TABLE ONLY public.detail_museum
    ADD COLUMN type_ref bigint;
ALTER TABLE ONLY public.detail_museum
    ADD COLUMN group_ref bigint;
ALTER TABLE ONLY public.detail_museum
    ADD COLUMN game_level_ref bigint;
ALTER TABLE ONLY public.detail_museum
    ADD COLUMN city_type_ref bigint;