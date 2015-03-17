-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- PostgreSQL version: 9.2
-- Project Site: pgmodeler.com.br
-- Model Author: ---

SET check_function_bodies = false;
-- ddl-end --


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: new_database | type: DATABASE --
-- CREATE DATABASE new_database
-- ;
-- -- ddl-end --
-- 


SET search_path TO public;
-- ddl-end --

-- object: user | type: TABLE --
CREATE TABLE "user"(
	id serial NOT NULL,
	name character varying(250) NOT NULL,
	login character varying(250) NOT NULL,
	password character varying(250) NOT NULL,
	role character varying(250) NOT NULL,
	currency character varying(250) NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT login_con UNIQUE (login)

);
-- ddl-end --
COMMENT ON TABLE "user" IS 'Таблица пользователей';
-- ddl-end --
-- ddl-end --

-- object: horce | type: TABLE --
CREATE TABLE horce(
	id serial NOT NULL,
	name character varying(250) NOT NULL,
	trainer character varying(250) NOT NULL,
	age smallint NOT NULL,
	CONSTRAINT horce_pk PRIMARY KEY (id),
	CONSTRAINT horce_un UNIQUE (name,trainer)

);
-- ddl-end --
COMMENT ON TABLE horce IS 'Справочник лошадей';
-- ddl-end --
-- ddl-end --

-- object: jockey | type: TABLE --
CREATE TABLE jockey(
	id serial NOT NULL,
	first_name character varying(250),
	last_name character varying(250),
	CONSTRAINT jockey_pk PRIMARY KEY (id),
	CONSTRAINT jockey_un UNIQUE (first_name,last_name)

);
-- ddl-end --
COMMENT ON TABLE jockey IS 'Справочник жокеев';
-- ddl-end --
-- ddl-end --

-- object: place | type: TABLE --
CREATE TABLE place(
	id serial NOT NULL,
	name character varying(250) NOT NULL,
	CONSTRAINT place_pk PRIMARY KEY (id),
	CONSTRAINT place_un UNIQUE (name)

);
-- ddl-end --
COMMENT ON TABLE place IS 'Справочник мест проведения';
-- ddl-end --
-- ddl-end --

-- object: race | type: TABLE --
CREATE TABLE race(
	id serial NOT NULL,
	place_id integer NOT NULL,
	name character varying(250) NOT NULL,
	distance character varying(250) NOT NULL,
	start timestamp NOT NULL,
	quantity smallint NOT NULL,
	CONSTRAINT race_pk PRIMARY KEY (id),
	CONSTRAINT race_un UNIQUE (name)

);
-- ddl-end --
COMMENT ON TABLE race IS 'Забеги';
-- ddl-end --
-- ddl-end --

-- object: runner | type: TABLE --
CREATE TABLE runner(
	id serial NOT NULL,
	horce_id integer NOT NULL,
	jockey_id integer NOT NULL,
	race_id integer NOT NULL,
	koefficient decimal(4,2) NOT NULL,
	place smallint,
	CONSTRAINT runner_pk PRIMARY KEY (id)

);
-- ddl-end --
COMMENT ON TABLE runner IS 'Участники забегов';
-- ddl-end --
-- ddl-end --

-- object: bet | type: TABLE --
CREATE TABLE bet(
	id serial NOT NULL,
	type character varying(250) NOT NULL,
	race_id integer NOT NULL,
	user_id integer NOT NULL,
	sum decimal(14,2) NOT NULL,
	CONSTRAINT bet_pk PRIMARY KEY (id)

);
-- ddl-end --
COMMENT ON TABLE bet IS 'Сделанные ставки';
-- ddl-end --
-- ddl-end --

-- object: race_place_fk | type: CONSTRAINT --
ALTER TABLE race ADD CONSTRAINT race_place_fk FOREIGN KEY (place_id)
REFERENCES place (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE NOT DEFERRABLE;
-- ddl-end --


-- object: runner_horce_fk | type: CONSTRAINT --
ALTER TABLE runner ADD CONSTRAINT runner_horce_fk FOREIGN KEY (horce_id)
REFERENCES horce (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: runner_jockey_fk | type: CONSTRAINT --
ALTER TABLE runner ADD CONSTRAINT runner_jockey_fk FOREIGN KEY (jockey_id)
REFERENCES jockey (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: runner_race_fk | type: CONSTRAINT --
ALTER TABLE runner ADD CONSTRAINT runner_race_fk FOREIGN KEY (race_id)
REFERENCES race (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;
-- ddl-end --


-- object: bet_user_fk | type: CONSTRAINT --
ALTER TABLE bet ADD CONSTRAINT bet_user_fk FOREIGN KEY (user_id)
REFERENCES "user" (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE NOT DEFERRABLE;
-- ddl-end --


-- object: bet_race_fk | type: CONSTRAINT --
ALTER TABLE bet ADD CONSTRAINT bet_race_fk FOREIGN KEY (race_id)
REFERENCES race (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE NOT DEFERRABLE;
-- ddl-end --



