CREATE TABLE public.detail_state(
    id  bigint NOT NULL,
    name character varying(255)
);
ALTER TABLE ONLY public.detail
    DROP COLUMN state;
ALTER TABLE ONLY public.detail
    ADD COLUMN reliability int;
ALTER TABLE ONLY public.detail
    ADD COLUMN creation_date date;
ALTER TABLE ONLY public.detail
    ADD COLUMN state_ref bigint;
ALTER TABLE ONLY public.detail
    ADD COLUMN start_episode_date date;

ALTER TABLE ONLY public.detail
    ADD CONSTRAINT detail_state FOREIGN KEY (state_ref) REFERENCES public.detail_state(id);