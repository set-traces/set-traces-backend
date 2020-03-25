--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

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
-- Name: project_users; Type: TABLE; Schema: public; Owner: settracesdev
--

CREATE TABLE public.project_users (
    project_id uuid NOT NULL,
    user_id uuid NOT NULL
);


ALTER TABLE public.project_users OWNER TO settracesdev;

--
-- Name: projects; Type: TABLE; Schema: public; Owner: settracesdev
--

CREATE TABLE public.projects (
    id uuid NOT NULL,
    description text,
    name character varying(255)
);


ALTER TABLE public.projects OWNER TO settracesdev;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: settracesdev
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(20)
);


ALTER TABLE public.roles OWNER TO settracesdev;

--
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: settracesdev
--


-- ALTER TABLE public.roles_id_seq OWNER TO settracesdev;

--
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: settracesdev
--

-- ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- Name: script_types; Type: TABLE; Schema: public; Owner: settracesdev
--

CREATE TABLE public.script_types (
    id uuid NOT NULL,
    name character varying(255),
    project_id uuid NOT NULL
);


ALTER TABLE public.script_types OWNER TO settracesdev;

--
-- Name: scripts; Type: TABLE; Schema: public; Owner: settracesdev
--

CREATE TABLE public.scripts (
    id uuid NOT NULL,
    description text,
    name character varying(255),
    project_id uuid NOT NULL,
    script_type_id uuid NOT NULL
);


ALTER TABLE public.scripts OWNER TO settracesdev;

--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: settracesdev
--

CREATE TABLE public.user_roles (
    user_id uuid NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.user_roles OWNER TO settracesdev;

--
-- Name: users; Type: TABLE; Schema: public; Owner: settracesdev
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    email character varying(255),
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.users OWNER TO settracesdev;

--
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: settracesdev
--

-- ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);

COMMIT;

--
-- Name: project_users project_users_pkey; Type: CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.project_users ADD CONSTRAINT project_users_pkey PRIMARY KEY (project_id, user_id);


--
-- Name: projects projects_pkey; Type: CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.projects ADD CONSTRAINT projects_pkey PRIMARY KEY (id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.roles ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: script_types script_types_pkey; Type: CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.script_types ADD CONSTRAINT script_types_pkey PRIMARY KEY (id);


--
-- Name: scripts scripts_pkey; Type: CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.scripts ADD CONSTRAINT scripts_pkey PRIMARY KEY (id);


--
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.users ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.users ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.user_roles ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.users ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: scripts fk2skkd60rfjdf8ibrjr1cms8ng; Type: FK CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.scripts ADD CONSTRAINT fk2skkd60rfjdf8ibrjr1cms8ng FOREIGN KEY (script_type_id) REFERENCES public.script_types(id);


--
-- Name: project_users fk8w55cu3qmg4yo0vy0b8e3ivk4; Type: FK CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.project_users ADD CONSTRAINT fk8w55cu3qmg4yo0vy0b8e3ivk4 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: user_roles fkh8ciramu9cc9q3qcqiv4ue8a6; Type: FK CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.user_roles ADD CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- Name: user_roles fkhfh9dx7w3ubf1co1vdev94g3f; Type: FK CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.user_roles ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: script_types fkj3wcyxjp4pkcx3fp49cfu8ndv; Type: FK CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.script_types ADD CONSTRAINT fkj3wcyxjp4pkcx3fp49cfu8ndv FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- Name: scripts fkk6vhofari8mcahd3ienkf9erg; Type: FK CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.scripts ADD CONSTRAINT fkk6vhofari8mcahd3ienkf9erg FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- Name: project_users fkn2d9w5xxgord5j4k2963p8o1g; Type: FK CONSTRAINT; Schema: public; Owner: settracesdev
--

ALTER TABLE ONLY public.project_users ADD CONSTRAINT fkn2d9w5xxgord5j4k2963p8o1g FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- PostgreSQL database dump complete
--
