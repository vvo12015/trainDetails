DROP TABLE IF EXISTS public.gameTM;

CREATE TABLE public.gameTM(
    id  bigint NOT NULL,
    map_ref bigint,
    game_level_ref bigint,
    current_gamer_ref bigint,
    game_state_ref bigint,
    profile json
);

ALTER TABLE ONLY public.gameTM
    ADD CONSTRAINT gametm_pkey PRIMARY KEY (id);

CREATE TABLE public.game_state(
    id  bigint NOT NULL,
    name character varying(255)
);

ALTER TABLE ONLY public.game_state
    ADD CONSTRAINT game_state_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.gameTM
    ADD CONSTRAINT gametm_game_level FOREIGN KEY (game_level_ref) REFERENCES public.game_level(id);

ALTER TABLE ONLY public.gameTM
    ADD CONSTRAINT gametm_game_state FOREIGN KEY (game_state_ref) REFERENCES public.game_state(id);

INSERT INTO game_level(id, name)
    values(1, 'beginner'),
    (2, 'middle'),
    (3, 'hard');

INSERT INTO game_state(id, name)
    values(1, 'begin'),
    (2, 'end');
