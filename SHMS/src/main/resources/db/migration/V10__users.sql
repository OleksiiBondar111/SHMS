CREATE TABLE public.users
(
    id                 character varying(32)  NOT NULL PRIMARY KEY,
    user_id            character varying(256) NOT NULL,
    first_name         character varying(32)  NOT NULL,
    last_name          character varying(32)  NOT NULL,
    email              character varying(32),
    encrypted_password character varying(250),
    deleted            timestamp without time zone,
    created            timestamp without time zone,
    modified           timestamp without time zone
);
