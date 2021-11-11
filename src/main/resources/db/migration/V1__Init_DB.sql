
CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE public.dictionary_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE public.progress_word_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE public.dictionary (
    id BIGINT NOT NULL DEFAULT nextval('public.dictionary_id_seq'::regclass),
    translation VARCHAR(128) NOT NULL,
    word VARCHAR(128) NOT NULL,
    level VARCHAR(2),
    img_name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE public.progress_word (
    id BIGINT NOT NULL DEFAULT nextval('public.progress_word_id_seq'::regclass),
    guessing_count INTEGER,
    last_revision_date TIMESTAMP WITHOUT TIME ZONE,
    learned_date TIMESTAMP WITHOUT TIME ZONE,
    revision_count INTEGER,
    studied BOOLEAN,
    word_id BIGINT,
    user_id BIGINT,
    PRIMARY KEY(id),
    UNIQUE(word_id, user_id)
);

create table user_role (
    user_id INT8 NOT NULL,
    roles VARCHAR(8)
);

CREATE TABLE public.users (
    id BIGINT NOT NULL DEFAULT nextval('public.user_id_seq'::regclass),
    login CHARACTER VARYING(64),
    name CHARACTER VARYING(32),
    password CHARACTER VARYING(255),
    date_of_birth DATE,
    e_mail CHARACTER VARYING(255),
    phone CHARACTER VARYING(13),
    avatar_name CHARACTER VARYING(255),
    level CHARACTER VARYING(255),
    score INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS progress_word ADD CONSTRAINT FK_Progress_Word_Users FOREIGN KEY (user_id) REFERENCES users;
ALTER TABLE IF EXISTS progress_word ADD CONSTRAINT FK_Progress_Word_Dictionary FOREIGN KEY (word_id) REFERENCES dictionary;
ALTER TABLE IF EXISTS user_role ADD CONSTRAINT FK_User_Role_Users FOREIGN KEY (user_id) REFERENCES users;
