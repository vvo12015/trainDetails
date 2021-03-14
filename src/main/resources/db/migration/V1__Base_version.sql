CREATE FUNCTION public.refresh_orders() RETURNS boolean
    LANGUAGE plpgsql
    AS $$
    BEGIN
        UPDATE orders
        SET state_id = 7
        WHERE orders.state_id = 1 AND orders.creation_date + (orders.waiting_deadline * interval '1 second') < now();
        UPDATE orders
        SET state_id = 3
        WHERE orders.state_id = 2 AND orders.creation_date + (orders.deadline1 * interval '1 second') < now();
        UPDATE orders
        SET state_id = 4
        WHERE orders.state_id = 3 AND orders.creation_date + (orders.deadline2 * interval '1 second') < now();
        DELETE FROM orders
        WHERE state_id = 7;
        RETURN true;
    END;
$$;

CREATE FUNCTION public.start_order(order_id bigint, train bigint) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
    BEGIN
        UPDATE orders
        SET active_date = now()
        WHERE id = order_id;
        UPDATE orders
        SET state_id = 2
        WHERE id = order_id;
        DELETE FROM orders
        WHERE (id != order_id) AND (train_id = train);
        RETURN true;
    END;
$$;


SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE public.cargo (
    id bigint NOT NULL,
    name character varying(255),
    min_price numeric(10,2),
    max_price numeric(10,2)
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
    user_id bigint,
    city_id bigint
);

CREATE TABLE public.detail (
    id bigint NOT NULL,
    name character varying(255),
    state smallint,
    distance_from_creation integer,
    distance_from_repair integer,
    train_id bigint NOT NULL,
    detail_museum_id bigint NOT NULL
);


CREATE TABLE public.detail_museum (
    id bigint NOT NULL,
    is_repaired boolean,
    name character varying(255),
    type character varying(255),
    wear smallint,
    mass integer,
    reliability integer,
    power integer
);

CREATE TABLE public.game_config (
    id bigint NOT NULL,
    name character varying(255),
    value_str character varying(255),
    value_int integer
);


ALTER TABLE public.game_config OWNER TO bpptcrbngilcyk;

CREATE TABLE public.order_state (
    id bigint NOT NULL,
    name character varying(255),
    in_motion boolean
);

CREATE TABLE public.orders (
    id bigint NOT NULL,
    car_count integer,
    creation_date timestamp without time zone DEFAULT now() NOT NULL,
    full_wear integer,
    profit integer,
    cargo_id bigint,
    route_id bigint,
    state_id bigint,
    train_id bigint,
    waiting_deadline bigint,
    deadline1 bigint,
    deadline2 bigint,
    execution integer,
    active_date timestamp without time zone
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
    train_museum_id bigint,
    city_id bigint,
    status_ref bigint
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

CREATE TABLE public.train_status (
    id bigint NOT NULL,
    name character varying(255)
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

COPY public.cargo (id, name, min_price, max_price) FROM stdin;
407	Gold	20.00	40.00
408	Coal	10.00	15.00
409	Oil	15.00	20.00
410	Water	2.00	15.00
411	Steel	20.00	30.00
1	Wood	5.00	10.00
\.

COPY public.city (id, name) FROM stdin;
1	Mukachevo
2	Uzhgorod
126	Lviv
238	Chop
239	Kiev
\.

COPY public.company (id, cash, name, train_count, user_id, city_id) FROM stdin;
5	45870	MyCompany	\N	4	1
3	-30057	MyCompany	\N	2	1
5242	-20040	MyCompany	\N	5241	1
1	18209	ValikCompany	1	1	1
\.

COPY public.detail (id, name, state, distance_from_creation, distance_from_repair, train_id, detail_museum_id) FROM stdin;
\.

COPY public.detail_museum (id, is_repaired, name, type, wear, mass, reliability, power) FROM stdin;
2951	t	Engine	type	10	600	90	1000
2950	t	Мотор    		100	600	80	1200
3563	t	Шасі    		10	400	99	0
\.

COPY public.game_config (id, name, value_str, value_int) FROM stdin;
4336	multiTrain	false	0
4339	adminPanel.cities	true	1
4340	adminPanel.routers	true	1
4343	userPanel.Resume	true	1
4344	userPanel.trainMuseum	true	1
4345	adminPanel.users	true	1
4341	adminPanel.orders	true	1
4346	adminPanel.trainMuseum	true	1
4348	adminPanel.gameConfig	true	1
4347	adminPanel.detail_museum	false	0
4338	adminPanel.cargo	true	1
\.

COPY public.order_state (id, name, in_motion) FROM stdin;
7	cancelled	\N
2	deadline1	t
3	deadline2	t
4	belated	t
5	done	f
6	belated_done	f
1	waiting	f
\.

COPY public.orders (id, car_count, creation_date, full_wear, profit, cargo_id, route_id, state_id, train_id, waiting_deadline, deadline1, deadline2, execution, active_date) FROM stdin;
1945	1	2018-11-24 07:53:42.12	10	1200	407	1	5	1833	34	34	40	100	2018-11-24 07:53:59.518
5371	8	2021-03-10 16:08:52.227	10	576	409	1	5	5243	40	40	48	100	2021-03-10 16:09:11.147
5384	8	2021-03-10 16:09:31.168	10	1392	411	1	5	5244	32	32	38	100	2021-03-10 16:10:38.73
1946	4	2018-11-24 07:54:28.029	10	256	1	1	5	1834	32	32	38	100	2018-11-24 07:54:56.185
6048	9	2021-03-13 19:50:10.906	10	864	411	1	5	1	40	40	48	100	2021-03-13 19:50:17.415
6055	8	2021-03-13 19:50:32.516	10	1056	407	1	5	5100	40	40	48	100	2021-03-13 20:14:55.651
6020	7	2021-03-13 19:18:26.82	10	992	407	1	5	4	34	34	40	100	2021-03-13 19:18:47.739
5878	3	2021-03-13 15:33:22.498	10	896	411	1	5	403	40	40	48	100	2021-03-13 15:33:45.329
5889	3	2021-03-13 15:33:22.63	10	896	411	1	5	5099	40	40	48	100	2021-03-13 15:33:35.239
5948	3	2021-03-13 15:33:57.01	10	1680	407	1	5	2429	0	0	0	100	2021-03-13 15:34:01.346
\.

COPY public.route (id, distance, city1, city2) FROM stdin;
1	40	1	2
\.

COPY public.train (id, corps_state, creation_date, name, company_id, train_museum_id, city_id, status_ref) FROM stdin;
292	0	2018-11-23 06:07:48.025	Train40	5	291	2	\N
404	45	2018-11-23 06:46:26.057	train20	5	2	2	\N
1835	100	2018-11-24 05:37:45.93	Train40	3	291	1	\N
1832	81	2018-11-24 05:37:28.672	train10	3	1	1	\N
1833	72	2018-11-24 05:37:38.788	train20	3	2	1	\N
1834	100	2018-11-24 05:37:40.973	train30	3	3	2	\N
5243	72	2021-03-10 13:31:46.398	train10	5242	1	1	\N
5244	72	2021-03-10 13:31:54.966	train30	5242	3	1	\N
405	36	2018-11-23 06:46:30.113	Train41	5	291	2	\N
406	32	2018-11-23 06:47:44.561	train21	5	2	1	\N
260	36	2018-11-23 06:03:51.682	train10	5	1	2	\N
4	13	2018-11-13 00:00:00	train20	1	2	2	\N
1	17	2018-11-05 00:00:00	ex_train1	1	1	1	\N
5100	40	2019-10-31 15:55:57.117	train13	1	1	2	1
2429	28	2018-11-24 12:06:23.633	Train40	1	291	1	\N
403	32	2018-11-23 06:45:35.228	train11	1	1	2	\N
5099	72	2019-10-31 15:55:56.713	train12	1	1	2	1
\.

COPY public.train_detail_museum (train_museum_id, detail_id) FROM stdin;
\.

COPY public.train_museum (id, corps_price, corps_wear, description, limit_age, mass, name, power, price, reliability, speed) FROM stdin;
1	10000	10	prime train	20	20000	train1	2000	15000	95	60
2	5000	15	second train	22	15000	train2	1500	10000	90	70
3	7500	10	third train	25	15000	train3	2500	25000	95	75
291	1	100	Cheat train	40	1	Train4	5000	1	100	5000
\.

COPY public.train_status (id, name) FROM stdin;
1	PROGRESS
2	SOLD
3	BREAKAGE
\.

COPY public.user_role (user_id, roles) FROM stdin;
1	USER
2	USER
4	USER
1	ADMIN
5241	USER
\.

COPY public.users (id, active, password, username) FROM stdin;
1	t	111	user
5241	t	111	admin
2	t	111	User
4	t	1111	Alex
\.

ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.detail_museum
    ADD CONSTRAINT detail_museum_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.detail
    ADD CONSTRAINT detail_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.game_config
    ADD CONSTRAINT game_config_pkey PRIMARY KEY (id);

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

ALTER TABLE ONLY public.train_status
    ADD CONSTRAINT train_status_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.company
    ADD CONSTRAINT city_company FOREIGN KEY (city_id) REFERENCES public.city(id);

ALTER TABLE ONLY public.train
    ADD CONSTRAINT city_train FOREIGN KEY (city_id) REFERENCES public.city(id);

ALTER TABLE ONLY public.detail
    ADD CONSTRAINT detail_detail_museum_id_fkey FOREIGN KEY (detail_museum_id) REFERENCES public.detail_museum(id);

ALTER TABLE ONLY public.detail
    ADD CONSTRAINT detail_train_id_fkey FOREIGN KEY (train_id) REFERENCES public.train(id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk9hgxphiqwqwjll7fryacgc261 FOREIGN KEY (train_id) REFERENCES public.train(id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkdqnsspy7dn65qeqxmlth3pwih FOREIGN KEY (state_id) REFERENCES public.order_state(id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkeop7en0d481ppxbnglcmxd5u9 FOREIGN KEY (route_id) REFERENCES public.route(id);

ALTER TABLE ONLY public.train_detail_museum
    ADD CONSTRAINT fkf2lxty4na57h32rtjg9yy37ch FOREIGN KEY (detail_id) REFERENCES public.detail_museum(id);

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

ALTER TABLE ONLY public.train
    ADD CONSTRAINT train_train_status FOREIGN KEY (status_ref) REFERENCES public.train_status(id);

GRANT ALL ON LANGUAGE plpgsql TO bpptcrbngilcyk;
