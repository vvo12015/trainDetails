CREATE TABLE public.game(
    id  bigint NOT NULL,
    game_level_ref bigint,
    game_time date,
    main_user_ref bigint
);

ALTER TABLE ONLY public.game
    ADD CONSTRAINT game_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.game
    ADD CONSTRAINT game_game_level FOREIGN KEY (game_level_ref) REFERENCES public.game_level(id);

ALTER TABLE ONLY public.game
    ADD CONSTRAINT game_user FOREIGN KEY (main_user_ref) REFERENCES public.users(id);

CREATE TABLE public.game_user(
    game_ref bigint,
    user_ref bigint
);

ALTER TABLE ONLY public.game_user
    ADD CONSTRAINT user_game_user FOREIGN KEY (user_ref) REFERENCES public.users(id);

ALTER TABLE ONLY public.game_user
    ADD CONSTRAINT game_game_user FOREIGN KEY (game_ref) REFERENCES public.game(id);
