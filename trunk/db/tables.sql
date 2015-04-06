SET search_path TO 'public';

CREATE TABLE user_account (
  id       SERIAL                 NOT NULL,
  name     CHARACTER VARYING(100) NOT NULL,
  login    CHARACTER VARYING(60)  NOT NULL,
  password CHARACTER VARYING(35)  NOT NULL,
  role     SMALLINT               NOT NULL DEFAULT 0,
  CONSTRAINT user_account_pkey PRIMARY KEY (id),
  CONSTRAINT user_account_login_key UNIQUE (login)
);
COMMENT ON TABLE user_account IS 'Таблица пользователей';

CREATE TABLE horce (
  id      SERIAL                 NOT NULL,
  name    CHARACTER VARYING(100) NOT NULL,
  trainer CHARACTER VARYING(100) NOT NULL,
  age     SMALLINT               NOT NULL,
  CONSTRAINT horce_pkey PRIMARY KEY (id),
  CONSTRAINT horce_name_trainer_key UNIQUE (name, trainer)
);
COMMENT ON TABLE horce IS 'Справочник лошадей';

CREATE TABLE jockey (
  id    SERIAL NOT NULL,
  fname CHARACTER VARYING(100),
  lname CHARACTER VARYING(100),
  CONSTRAINT jockey_pkey PRIMARY KEY (id),
  CONSTRAINT jockey_fname_lname_key UNIQUE (fname, lname)
);
COMMENT ON TABLE jockey IS 'Справочник жокеев';

CREATE TABLE place (
  id   SERIAL                 NOT NULL,
  name CHARACTER VARYING(100) NOT NULL,
  CONSTRAINT place_pkey PRIMARY KEY (id),
  CONSTRAINT place_name_key UNIQUE (name)
);
COMMENT ON TABLE place IS 'Справочник мест проведения';

CREATE TABLE race (
  id          SERIAL                      NOT NULL,
  place_id    INTEGER                     NOT NULL,
  description CHARACTER VARYING(250)      NOT NULL,
  distance    CHARACTER VARYING(50)       NOT NULL,
  start       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  quantity    SMALLINT                    NOT NULL,
  CONSTRAINT race_pkey PRIMARY KEY (id),
  CONSTRAINT race_description_key UNIQUE (description),
  CONSTRAINT race_place_id_fkey FOREIGN KEY (place_id)
  REFERENCES place (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);
COMMENT ON TABLE race IS 'Забеги';

CREATE TABLE runner (
  id          SERIAL        NOT NULL,
  horce_id    INTEGER       NOT NULL,
  jockey_id   INTEGER       NOT NULL,
  race_id     INTEGER       NOT NULL,
  koefficient DECIMAL(8, 4) NOT NULL,
  place       SMALLINT,
  CONSTRAINT runner_pkey PRIMARY KEY (id),
  CONSTRAINT runner_horce_id_fkey FOREIGN KEY (horce_id)
  REFERENCES horce (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT runner_jockey_id_fkey FOREIGN KEY (jockey_id)
  REFERENCES jockey (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT runner_race_id_fkey FOREIGN KEY (race_id)
  REFERENCES race (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);
COMMENT ON TABLE runner IS 'Участники забегов';

CREATE TABLE bet (
  id              SERIAL         NOT NULL,
  type            SMALLINT       NOT NULL DEFAULT 0,
  race_id         INTEGER        NOT NULL,
  user_account_id INTEGER        NOT NULL,
  currency        SMALLINT       NOT NULL DEFAULT 0,
  sum             DECIMAL(14, 2) NOT NULL,
  CONSTRAINT bet_pkey PRIMARY KEY (id),
  CONSTRAINT bet_race_id_fkey FOREIGN KEY (race_id)
  REFERENCES race (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT bet_user_account_id_fkey FOREIGN KEY (user_account_id)
  REFERENCES user_account (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);
COMMENT ON TABLE bet IS 'Сделанные ставки';