--
-- PostgreSQL database dump
--

-- Dumped from database version 14.18 (Ubuntu 14.18-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.18 (Ubuntu 14.18-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: avatar; Type: TABLE; Schema: public; Owner: genli
--

CREATE TABLE public.avatar (
    id integer NOT NULL,
    image_url character varying(255) NOT NULL,
    name character varying(50) NOT NULL,
    game_type character varying(50) NOT NULL
);


ALTER TABLE public.avatar OWNER TO genli;

--
-- Name: avatar_id_seq; Type: SEQUENCE; Schema: public; Owner: genli
--

CREATE SEQUENCE public.avatar_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.avatar_id_seq OWNER TO genli;

--
-- Name: avatar_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: genli
--

ALTER SEQUENCE public.avatar_id_seq OWNED BY public.avatar.id;


--
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: genli
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO genli;

--
-- Name: leaderboard; Type: TABLE; Schema: public; Owner: genli
--

CREATE TABLE public.leaderboard (
    id integer NOT NULL,
    profile_id integer,
    score integer NOT NULL,
    game_type character varying(100) NOT NULL,
    date timestamp without time zone NOT NULL
);


ALTER TABLE public.leaderboard OWNER TO genli;

--
-- Name: leaderboard_id_seq; Type: SEQUENCE; Schema: public; Owner: genli
--

CREATE SEQUENCE public.leaderboard_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.leaderboard_id_seq OWNER TO genli;

--
-- Name: leaderboard_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: genli
--

ALTER SEQUENCE public.leaderboard_id_seq OWNED BY public.leaderboard.id;


--
-- Name: profile; Type: TABLE; Schema: public; Owner: genli
--

CREATE TABLE public.profile (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    password_hash character varying(255) NOT NULL,
    profile_photo character varying(255),
    race_game_avatar_id bigint,
    baloon_game_avatar_id bigint,
    hangman_game_avatar_id bigint
);


ALTER TABLE public.profile OWNER TO genli;

--
-- Name: profile_id_seq; Type: SEQUENCE; Schema: public; Owner: genli
--

CREATE SEQUENCE public.profile_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profile_id_seq OWNER TO genli;

--
-- Name: profile_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: genli
--

ALTER SEQUENCE public.profile_id_seq OWNED BY public.profile.id;


--
-- Name: avatar id; Type: DEFAULT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.avatar ALTER COLUMN id SET DEFAULT nextval('public.avatar_id_seq'::regclass);


--
-- Name: leaderboard id; Type: DEFAULT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.leaderboard ALTER COLUMN id SET DEFAULT nextval('public.leaderboard_id_seq'::regclass);


--
-- Name: profile id; Type: DEFAULT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.profile ALTER COLUMN id SET DEFAULT nextval('public.profile_id_seq'::regclass);


--
-- Name: avatar avatar_pkey; Type: CONSTRAINT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.avatar
    ADD CONSTRAINT avatar_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: leaderboard leaderboard_pkey; Type: CONSTRAINT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.leaderboard
    ADD CONSTRAINT leaderboard_pkey PRIMARY KEY (id);


--
-- Name: leaderboard leaderboard_profile_id_game_type_key; Type: CONSTRAINT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.leaderboard
    ADD CONSTRAINT leaderboard_profile_id_game_type_key UNIQUE (profile_id, game_type);


--
-- Name: profile profile_email_key; Type: CONSTRAINT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_email_key UNIQUE (email);


--
-- Name: profile profile_pkey; Type: CONSTRAINT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);


--
-- Name: profile profile_username_key; Type: CONSTRAINT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_username_key UNIQUE (username);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: genli
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: leaderboard leaderboard_profile_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.leaderboard
    ADD CONSTRAINT leaderboard_profile_id_fkey FOREIGN KEY (profile_id) REFERENCES public.profile(id);


--
-- Name: profile profile_baloon_game_avatar_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_baloon_game_avatar_id_fkey FOREIGN KEY (baloon_game_avatar_id) REFERENCES public.avatar(id);


--
-- Name: profile profile_hangman_game_avatar_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_hangman_game_avatar_id_fkey FOREIGN KEY (hangman_game_avatar_id) REFERENCES public.avatar(id);


--
-- Name: profile profile_race_game_avatar_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: genli
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_race_game_avatar_id_fkey FOREIGN KEY (race_game_avatar_id) REFERENCES public.avatar(id);


--
-- PostgreSQL database dump complete
--

