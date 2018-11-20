SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

CREATE TABLE public.cargo (
    id bigint NOT NULL,
    name character varying(255)
);

CREATE TABLE public.city (
    id bigint NOT NULL,
    name character varying(255)
);


CREATE TABLE public.company (
    id bigint NOT NULL,
    cash real,
    name character varying(255),
    train_count integer,
    user_id bigint
);

CREATE TABLE public.detail_museum (
    id bigint NOT NULL,
    is_repaired boolean,
    name character varying(255),
    type character varying(255),
    wear smallint
);

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.order_state (
    id bigint NOT NULL,
    name character varying(255),
    in_motion boolean
);

CREATE TABLE public.orders (
    id bigint NOT NULL,
    car_count integer,
    creation_date timestamp without time zone,
    deadline1 timestamp without time zone,
    deadline2 timestamp without time zone,
    full_wear integer,
    profit integer,
    waiting_deadline timestamp without time zone,
    cargo_id bigint,
    route_id bigint,
    state_id bigint,
    train_id bigint
);

CREATE TABLE public.route (
    id bigint NOT NULL,
    distance integer,
    city1 bigint,
    city2 bigint
);


CREATE TABLE public.train (
    id bigint NOT NULL,
    corps_state smallint,
    creation_date timestamp without time zone,
    name character varying(255),
    company_id bigint,
    train_museum_id bigint
);


CREATE TABLE public.train_detail_museum (
    train_museum_id bigint NOT NULL,
    detail_id bigint NOT NULL
);

CREATE TABLE public.train_museum (
    id bigint NOT NULL,
    corps_price integer,
    corps_wear smallint,
    description character varying(255),
    limit_age smallint,
    mass integer,
    name character varying(255),
    power integer,
    price integer,
    reliability smallint,
    speed integer
);


CREATE TABLE public.user_role (
    user_id bigint NOT NULL,
    roles character varying(255)
);


CREATE TABLE public.users (
    id bigint NOT NULL,
    active boolean NOT NULL,
    password character varying(255),
    username character varying(255)
);


COPY public.cargo (id, name) FROM stdin;
1	wood
\.


COPY public.city (id, name) FROM stdin;
1	Mukachevo
2	Uzhgorod
\.


COPY public.company (id, cash, name, train_count, user_id) FROM stdin;
1	5000	ValikCompany	1	1
2	0	MyCompany	\N	\N
\.

COPY public.detail_museum (id, is_repaired, name, type, wear) FROM stdin;
\.

COPY public.order_state (id, name) FROM stdin;
1	wainting
2	deadline1
3	deadline2
4	belated
5	done
6	belated_done
7	cancelled
\.

COPY public.orders (id, car_count, creation_date, deadline1, deadline2, full_wear, profit, waiting_deadline, cargo_id, route_id, state_id, train_id) FROM stdin;
1	3	2018-11-05 00:00:00	2018-11-05 00:03:00	2018-11-05 00:05:00	10	2000	2018-11-05 00:01:00	1	1	7	1
\.

COPY public.route (id, distance, city1, city2) FROM stdin;
1	40	1	2
\.

COPY public.train (id, corps_state, creation_date, name, company_id, train_museum_id) FROM stdin;
1	100	2018-11-05 00:00:00	ex_train1	1	1
4	100	\N	train20	1	2
\.


COPY public.train_detail_museum (train_museum_id, detail_id) FROM stdin;
\.

COPY public.train_museum (id, corps_price, corps_wear, description, limit_age, mass, name, power, price, reliability, speed) FROM stdin;
1	10000	10	prime train	20	20000	train1	2000	15000	95	60
2	5000	15	second train	22	15000	train2	1500	10000	90	70
3	7500	10	third train	25	15000	train3	2500	25000	95	75
\.


COPY public.user_role (user_id, roles) FROM stdin;
1	USER
\.

COPY public.users (id, active, password, username) FROM stdin;
1	t	111	user
\.

ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.detail_museum
    ADD CONSTRAINT detail_museum_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.order_state
    ADD CONSTRAINT order_state_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.route
    ADD CONSTRAINT route_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.train_museum
    ADD CONSTRAINT train_museum_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.train
    ADD CONSTRAINT train_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk9hgxphiqwqwjll7fryacgc261 FOREIGN KEY (train_id) REFERENCES public.train(id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkdqnsspy7dn65qeqxmlth3pwih FOREIGN KEY (state_id) REFERENCES public.order_state(id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkeop7en0d481ppxbnglcmxd5u9 FOREIGN KEY (route_id) REFERENCES public.route(id);


ALTER TABLE ONLY public.train_detail_museum
    ADD CONSTRAINT fkf2lxty4na57h32rtjg9yy37ch FOREIGN KEY (detail_id) REFERENCES public.train_museum(id);

ALTER TABLE ONLY public.train
    ADD CONSTRAINT fkfcd7gfk198fistapjl3u6xx2p FOREIGN KEY (train_museum_id) REFERENCES public.train_museum(id);

ALTER TABLE ONLY public.train
    ADD CONSTRAINT fkfmt6w8ufr8mjsyj7qxgh1q6w0 FOREIGN KEY (company_id) REFERENCES public.company(id);

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fkj345gk1bovqvfame88rcx7yyx FOREIGN KEY (user_id) REFERENCES public.users(id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fko2hmyoymmbiohcu2fpgxeg9tc FOREIGN KEY (cargo_id) REFERENCES public.cargo(id);

ALTER TABLE ONLY public.train_detail_museum
    ADD CONSTRAINT fkpdval0snai5dos6g87x5spc6v FOREIGN KEY (train_museum_id) REFERENCES public.train_museum(id);

ALTER TABLE ONLY public.route
    ADD CONSTRAINT fksp2dr6a6wae4twh43n2bm5y0q FOREIGN KEY (city1) REFERENCES public.city(id);

ALTER TABLE ONLY public.company
    ADD CONSTRAINT fksxe9t9istcdt2mtdbvgh83a9g FOREIGN KEY (user_id) REFERENCES public.users(id);

ALTER TABLE ONLY public.route
    ADD CONSTRAINT fktnnd4ysd4bg329yie7qh4x0lv FOREIGN KEY (city2) REFERENCES public.city(id);

CREATE OR REPLACE FUNCTION public.refresh_orders()
  RETURNS boolean AS
$BODY$
    BEGIN
        UPDATE orders
        SET state_id = 7
        WHERE orders.state_id = 1 AND orders.waiting_deadline < CURRENT_DATE + CURRENT_TIME;
        UPDATE orders
        SET state_id = 3
        WHERE orders.state_id = 2 AND orders.deadline1 < CURRENT_DATE + CURRENT_TIME;
        UPDATE orders
        SET state_id = 4
        WHERE orders.state_id = 3 AND orders.deadline2 < CURRENT_DATE + CURRENT_TIME;
        DELETE FROM orders
        WHERE state_id = 7;
        RETURN true;
    END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

