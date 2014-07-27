# Echos schema

# --- !Ups

CREATE SEQUENCE echo_id_seq;
CREATE TABLE echo (
    id integer NOT NULL DEFAULT nextval('echo_id_seq'),
    message varchar(255)
);

# --- !Downs

DROP TABLE echo;
DROP SEQUENCE echo_id_seq;