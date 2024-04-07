--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.2

-- Started on 2024-04-06 13:24:54 -03

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

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

--CREATE SCHEMA public;
set  schema 'public';

ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16407)
-- Name: person; Type: TABLE; Schema: public; Owner: quarkus
--

CREATE TABLE public.person (
                               id uuid NOT NULL,
                               dateofbirth date NOT NULL,
                               name character varying(100) NOT NULL,
                               nickname character varying(32) NOT NULL,
                               searchable character varying(255)
);


ALTER TABLE public.person OWNER TO quarkus;

--
-- TOC entry 216 (class 1259 OID 16410)
-- Name: stack; Type: TABLE; Schema: public; Owner: quarkus
--

CREATE TABLE public.stack (
                              id uuid NOT NULL,
                              name character varying(32) NOT NULL,
                              personid uuid NOT NULL
);


ALTER TABLE public.stack OWNER TO quarkus;

--
-- TOC entry 3210 (class 2606 OID 16414)
-- Name: stack stack_pkey; Type: CONSTRAINT; Schema: public; Owner: quarkus
--

ALTER TABLE ONLY public.stack
    ADD CONSTRAINT stack_pkey PRIMARY KEY (id);


--
-- TOC entry 3207 (class 2606 OID 16416)
-- Name: person uk_8afbko4lhgfokxvxnygwxskrp; Type: CONSTRAINT; Schema: public; Owner: quarkus
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT uk_nickname UNIQUE (nickname);


--
-- TOC entry 3208 (class 1259 OID 16417)
-- Name: idxolwwmxpb7oqq1ly22y25jxnjd; Type: INDEX; Schema: public; Owner: quarkus
--

CREATE INDEX idx_stack_person_id ON public.stack USING btree (personid);

CREATE INDEX idx_id ON person (id);
CREATE EXTENSION IF NOT EXISTS pg_trgm;
-- CREATE INDEX IF NOT EXISTS idx_pessoas_searchable ON public.person USING gin (searchable public.gin_trgm_ops);
CREATE INDEX IF NOT EXISTS idx_pessoas_searchable ON public.person USING gist (searchable public.gist_trgm_ops (siglen='64'));
SET statement_timeout = '1s';
-- Completed on 2024-04-06 13:24:54 -03

--
-- PostgreSQL database dump complete
--
