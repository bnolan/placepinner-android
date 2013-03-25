drop table places;

CREATE TABLE places (
    latitude double precision,
    longitude double precision,
    name character varying(255),
    address character varying(255),
    phone character varying(255),
    website character varying(255),
    notes text,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    slug character varying(255),
    reverse_geocode text,
    country character varying(255),
    admin_area character varying(255),
    locality character varying(255),
    user_id integer,
    transit text,
    photo character varying(255),
    id integer primary key asc,
    changed boolean default false,
    uuid character varying(255)
);
