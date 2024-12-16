CREATE TABLE public.users
(
    id         character varying(32) NOT NULL PRIMARY KEY,
    first_name character varying(32) NOT NULL,
    last_name  character varying(32) NOT NULL,
    email      character varying(32),
    password   character varying(250),
    role       character varying(32)
);
