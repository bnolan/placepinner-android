drop table places;

CREATE TABLE places (
    id integer primary key asc,
    name character varying(255),
    uuid character varying(255),
    address character varying(255),
    notes text,
    country character varying(255),
    phone character varying(255),
    website character varying(255),
    locality character varying(255),
    latitude double precision,
    longitude double precision,
    created_at timestamp without time zone
);
