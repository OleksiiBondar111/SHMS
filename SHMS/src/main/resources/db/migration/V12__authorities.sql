CREATE TABLE public.authorities
(
    id           character varying(32) NOT NULL PRIMARY KEY,
    name         character varying(32)
);


CREATE TABLE public.users_roles
(
    user_id character varying(32) REFERENCES public.users (id) ON DELETE SET NULL,
    role_id character varying(32) REFERENCES public.roles (id) ON DELETE SET NULL
);

CREATE TABLE public.roles_authorities
(
    role_id             character varying(32) REFERENCES public.roles (id) ON DELETE SET NULL,
    authority_id      character varying(32) REFERENCES public.authorities (id) ON DELETE SET NULL
);