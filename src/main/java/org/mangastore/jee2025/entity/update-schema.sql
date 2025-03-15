CREATE TABLE manga
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title       VARCHAR(255)                            NOT NULL,
    pages       INTEGER                                 NOT NULL,
    author      VARCHAR(255)                            NOT NULL,
    isbn        VARCHAR(255)                            NOT NULL,
    description VARCHAR(255),
    date        VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_manga PRIMARY KEY (id)
);

ALTER TABLE manga
    ADD CONSTRAINT uc_manga_isbn UNIQUE (isbn);

DROP TABLE manga