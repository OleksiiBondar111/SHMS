CREATE TABLE public.token
(
    id         character varying(32) NOT NULL PRIMARY KEY,
    user_id    character varying(32) REFERENCES public.users (id) ON DELETE SET NULL,
    token      character varying(4000) NOT NULL,
    token_type character varying(32) NOT NULL,
    expired    boolean,
    revoked    boolean
);
