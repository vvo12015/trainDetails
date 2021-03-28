ALTER TABLE ONLY public.detail_museum_type
    ADD COLUMN game_level_ref bigint;
ALTER TABLE ONLY public.detail_museum_type
    ADD COLUMN train_type_ref bigint;

ALTER TABLE ONLY public.detail_museum_type
    ADD CONSTRAINT detail_museum_type_game_level FOREIGN KEY (game_level_ref) REFERENCES public.game_level(id);

ALTER TABLE ONLY public.detail_museum_type
    ADD CONSTRAINT detail_museum_type_train_type FOREIGN KEY (train_type_ref) REFERENCES public.train_type(id);