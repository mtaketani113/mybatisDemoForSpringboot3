CREATE TABLE IF NOT EXISTS customer (
    id         INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    name       TEXT,
    post       TEXT,
    address    TEXT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS file_manager (
    id          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    file_name   TEXT,
    file_data   MEDIUMBLOB,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY(id)
);

DELETE FROM customer;
DELETE FROM file_manager;