

-- creating table for actor lines
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS actors (
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    actor varchar(50),
    user_id uuid,
    CONSTRAINT fk_key_user_actor_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT pkey_actors PRIMARY KEY ( id )
);

CREATE TABLE IF NOT EXISTS playing_roles (
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    role varchar(100),
    actor_id uuid,
    CONSTRAINT fk_actor_playing_roles_actor_id FOREIGN KEY (actor_id) REFERENCES actors(id),
    CONSTRAINT pkey_playing_roles PRIMARY KEY ( id )
);


CREATE TABLE IF NOT EXISTS lines (
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    type varchar(45) default 'REMARK',
    text TEXT,
    CONSTRAINT pkey_lines PRIMARY KEY ( id )
);
