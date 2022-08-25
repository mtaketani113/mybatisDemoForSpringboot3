CREATE TABLE IF NOT EXISTS customer (
    id       INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    name     TEXT,
    post     TEXT,
    address  TEXT,

    PRIMARY KEY(id)
);

DELETE FROM customer;