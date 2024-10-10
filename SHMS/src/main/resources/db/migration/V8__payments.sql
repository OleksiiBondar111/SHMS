CREATE TABLE public.payments
(
    id             character varying(32) NOT NULL PRIMARY KEY,
    invoice_id     character varying(32) REFERENCES public.invoices (id) ON DELETE SET NULL,
    deleted        timestamp without time zone,
    created        timestamp without time zone,
    modified       timestamp without time zone,
    payment_method character varying(50),
    payment_date   timestamp without time zone,
    payment_amount decimal(10, 2)
);	