--liquibase formatted sql

--changeset nvoxland:1
CREATE SCHEMA scrapper;
CREATE TABLE scrapper.hyperlink(
    link_id SERIAL PRIMARY KEY,
    last_modified TIMESTAMP NOT NULL,
    url VARCHAR(220)
);
CREATE TABLE scrapper.chat(
    chat_id SERIAL PRIMARY KEY,
    telegram_chat_id BIGINT NOT NULL
);
CREATE TABLE scrapper.hyperlink_chat(
    link_id INT NOT NULL,
    chat_id INT NOT NULL,
    FOREIGN KEY (link_id) REFERENCES scrapper.hyperlink (link_id),
    FOREIGN KEY (chat_id) REFERENCES scrapper.chat (chat_id)
);