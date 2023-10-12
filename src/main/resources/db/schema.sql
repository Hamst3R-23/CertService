CREATE TABLE IF NOT EXISTS USERS (
    id BIGINT PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ROLES (
    id BIGINT PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS CERTIFICATES (
    id BIGINT PRIMARY KEY,
    fingerprint varchar(255) NOT NULL,
    fingerprintalgorithm varchar(255) NOT NULL,
    subject varchar(255) NOT NULL,
    user_id bigint references USERS(id) on delete cascade
);

CREATE TABLE IF NOT EXISTS USERS_ROLES (
user_id BIGINT references USERS(id),
roles_id BIGINT references ROLES(id)
);



