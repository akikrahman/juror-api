do
$$begin if not exists (select * from pg_catalog.pg_roles where rolname = 'juror_er') then
    CREATE ROLE juror_er WITH
        NOSUPERUSER
        NOCREATEDB
        NOCREATEROLE
        INHERIT
        LOGIN
        NOREPLICATION
        NOBYPASSRLS
        CONNECTION LIMIT -1;
end if;end
$$
;

CREATE SCHEMA IF NOT EXISTS juror_er;



CREATE TABLE juror_er.local_authority (
      la_code varchar(3) NOT NULL,
      la_name varchar(100) NULL,
      is_active boolean,
      upload_status varchar(40) NULL,  -- NOT_UPLOADED, UPLOADED
      CONSTRAINT local_authority_pkey PRIMARY KEY (la_code)
);


ALTER TABLE juror_er.local_authority
    ADD CONSTRAINT upload_status_value_check CHECK (((upload_status)::text = ANY (
        (ARRAY ['UPLOADED'::character varying, 'NOT_UPLOADED'::character varying]))));


--INSERT INTO juror_er.local_authority (la_code,la_name,is_active,upload_status) VALUES
--	 ('020','MY_LOCAL_AUTH',true,'NOT_UPLOADED');

CREATE TABLE juror_er.user (
     username varchar(200) NOT NULL, -- this is the email address of the user
     la_code  varchar(3) NOT NULL,
     active bool NOT NULL DEFAULT true,
     last_logged_in timestamp(6) NULL,
   	 updated_by varchar(20) NOT NULL,
   	 last_updated timestamp(0) NULL,
   	 CONSTRAINT local_authority_fk FOREIGN KEY (la_code) REFERENCES juror_er
        .local_authority (la_code),
     CONSTRAINT user_pkey PRIMARY KEY (username)
);


--INSERT INTO juror_er."user" (username,la_code,active,last_logged_in,updated_by,last_updated) VALUES
--	 ('testuseremail@testauth.gov.uk','020',true,NULL,'bureau_user',NULL);


